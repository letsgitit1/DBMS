package UserDao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection
;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import UserVo.H_MEMBER_VO;

public class H_MEMBER_DAO {
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	//싱글톤 객체 생성
	private static H_MEMBER_DAO dao=new H_MEMBER_DAO();
	private H_MEMBER_DAO() {}
	public static H_MEMBER_DAO getMemberDao() { return dao;}
	
		//회원 정보 삽입
			public boolean insert(H_MEMBER_VO member) throws SQLException {
				boolean check=false;
				conn = DBConnecter.getConnection();
				String query="INSERT INTO H_MEMBER\r\n"
						+ "(MEMBER_NO, TRAINER_NO, NAME, ID, PASSWORD, PHONE, GENDER, BIRTH_DATE)\r\n"
						+ "VALUES(SQ_MEMBER_NO.nextval,?,?,?,?,?,?,?)";
					pstm= conn.prepareStatement(query);
					pstm.setInt(1, member.getTRAINER_NO());
					pstm.setString(2, member.getName());
					pstm.setString(3, member.getID());
					pstm.setString(4, member.getPW());
					pstm.setString(5, member.getPHONE());
					pstm.setString(6, member.getGENDER());
					pstm.setString(7, member.getBIRTH_DATE());
					if(pstm.executeUpdate()==1) {check=true;}
					if(pstm!=null) {pstm.close();}
					if(conn!=null) {conn.close();}
					return check;
			}
			
			//아이디 중복 검사
			public boolean checkId(String id) {
				String query="SELECT COUNT(ID) FROM H_MEMBER WHERE ID=?";
				boolean check=false;
				try {
					conn=DBConnecter.getConnection();
					pstm=conn.prepareStatement(query);
					pstm.setString(1,id);
					rs=pstm.executeQuery();
					rs.next();
					if(rs.getInt(1)==0) {check=true;}
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					try {
					if(rs!=null) {rs.close();}
					if(pstm!=null) {rs.close();}	
					if(conn!=null) {rs.close();}	
					}catch(SQLException e) {
					e.printStackTrace();
					}
				}
				return check;
			}
			//암호화
			public byte[] sha256(String msg) throws NoSuchAlgorithmException{
				MessageDigest md= MessageDigest.getInstance("SHA-256");
				md.update(msg.getBytes());
				return md.digest();
				
			}
			public String bytesToHex(byte[] bytes) {
				StringBuilder builder=new StringBuilder();
				for(byte b:bytes) {
					builder.append(String.format("%02x", b));
				}
				return builder.toString();
			}
			//로그인
			public boolean login(String id,String pw) throws NoSuchAlgorithmException, SQLException {
				boolean check=false;
				String query="SELECT COUNT(ID)FROM H_MEMBER \r\n"
						+ "WHERE ID=? AND PASSWORD=?";
					conn=DBConnecter.getConnection();
					pstm=conn.prepareStatement(query);
					pstm.setString(1, id);
					pstm.setString(2, bytesToHex(sha256(pw)));
					rs=pstm.executeQuery();
					rs.next();
					
					if(rs.getInt(1)==1) {check=true;}
					return check;
				}
			//전화번호 수정
			public boolean UpdatePh(String phone,String ID) throws SQLException {
				boolean check=false;
				String query="UPDATE H_MEMBER\r\n"
						+ "SET PHONE = ?"
						+ "WHERE ID =?";
				conn=DBConnecter.getConnection();
				pstm=conn.prepareStatement(query);
				pstm.setString(1, phone);
				pstm.setString(2,ID);
				if(pstm.executeUpdate()==1) {check=true;}
				if(pstm!=null) {pstm.close();}
				if(conn!=null) {conn.close();}
				return check;
			}
			//비밀번호 수정
			public boolean UpdatePw(String pw,String ID) throws SQLException {
				boolean check=false;
				String query="UPDATE H_MEMBER\r\n"
						+ "SET PASSWORD= ?"
						+ "WHERE ID =?";
				conn=DBConnecter.getConnection();
				pstm=conn.prepareStatement(query);
				pstm.setString(1,pw);
				pstm.setString(2, ID);
				if(pstm.executeUpdate()==1) {check=true;}
				if(pstm!=null) {pstm.close();}
				if(conn!=null) {conn.close();}
				return check;
			}
			//회원 아이디로 회원 번호 추출
			public int extractMEM_NO(String ID) throws SQLException {
				conn=DBConnecter.getConnection();
				String query="SELECT MEMBER_NO FROM H_MEMBER\r\n"
						+ "WHERE id=?";
				pstm=conn.prepareStatement(query);
				pstm.setString(1,ID);
				rs=pstm.executeQuery();
				rs.next();
				return rs.getInt(1);
			}
}			


