package UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import UserVo.H_MEMBER_JOIN_TRAINER_VO;

public class MEMBER_JOIN_TRAINER_DAO {
	Connection conn=null;
	PreparedStatement pstm=null;
	ResultSet rs= null;
	private static MEMBER_JOIN_TRAINER_DAO dao=new MEMBER_JOIN_TRAINER_DAO();
	private MEMBER_JOIN_TRAINER_DAO() {};
	public static MEMBER_JOIN_TRAINER_DAO getMTJ() { return dao;}
	
	//회원 정보 출력
	public H_MEMBER_JOIN_TRAINER_VO memberInfo(String id) throws SQLException{
		H_MEMBER_JOIN_TRAINER_VO h=null;
		Connection conn=DBConnecter.getConnection();
		String query="SELECT H.NAME,H.MEMBER_NO,H.ID,H.PHONE,T.NAME,TO_CHAR(BIRTH_DATE,'YYYY-MM-DD')  \r\n"
				+ "FROM H_MEMBER H JOIN TRAINER T\r\n"
				+ "ON T.TRAINER_NO=H.TRAINER_NO AND H.ID=?";
		pstm=conn.prepareStatement(query);
		pstm.setString(1, id);
		rs=pstm.executeQuery();
		if(rs.next()) {
			h=new H_MEMBER_JOIN_TRAINER_VO(
					rs.getString(1),
					rs.getInt(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6)
					);
		}
		if(rs!=null) {rs.close();}
		if(pstm!=null) {pstm.close();}
		if(conn!=null) {conn.close();}
		return h;
	}
}
