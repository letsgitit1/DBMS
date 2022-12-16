package UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import UserVo.ENROLL_VO;
import UserVo.H_MEMBER_VO;
import UserVo.PRODUCT_VO;

public class ENROLL_DAO {
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	private static ENROLL_DAO dao= new ENROLL_DAO();
	private  ENROLL_DAO() {	}
	public static ENROLL_DAO getEnrollDao() {return dao;}
	H_MEMBER_DAO dao2=H_MEMBER_DAO.getMemberDao();
	PRODUCT_DAO dao3=PRODUCT_DAO.getPRODUCT_DAO();
	//등록 테이블 데이터 삽입
	public boolean insertEnroll(int P_NO,String ID) throws SQLException {
		PRODUCT_VO vo2;
		boolean check=false;
		String sql="INSERT INTO ENROLL\r\n"
				+ "(ENROLL_NO, PRODUCT_NO, MEMBER_NO, JOIN_DATE, Remainingcount)\r\n"
				+ "VALUES(SQ_ENROLL_NO.nextval, ?, ?, SYSDATE,?)";
		conn=DBConnecter.getConnection();
		pstm=conn.prepareStatement(sql);
		pstm.setInt(1,P_NO);
		pstm.setInt(2, dao2.extractMEM_NO(ID));
		pstm.setInt(3, dao3.peirod_extract(P_NO));
		if(pstm.executeUpdate()==1) {check=true;}
		if(pstm!=null) {pstm.close();}
		if(conn!=null) {conn.close();}
		return check;
	}
	
}
