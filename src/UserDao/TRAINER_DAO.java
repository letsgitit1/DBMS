package UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import UserVo.TRANINER_VO;
public  class TRAINER_DAO{
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	private static TRAINER_DAO dao= new TRAINER_DAO();
	private TRAINER_DAO() {}
	public static TRAINER_DAO getTrainerDao() {return dao;}
	//트레이너 전체 출력 메소드
	public List<TRANINER_VO> trainerInfo() throws SQLException {
		List<TRANINER_VO> list = new ArrayList<>();
		Connection conn = DBConnecter.getConnection();
		String query="SELECT * FROM TRAINER";
		pstm=conn.prepareStatement(query);
		rs=pstm.executeQuery();
		while(rs.next()) {
			TRANINER_VO t= new TRANINER_VO(
			rs.getInt(1),
			rs.getString(2),
			rs.getString(3),
			rs.getString(4),
			rs.getString(5)
			);
			list.add(t);
		}
		
		conn.close();
		pstm.close();
		rs.close();
		return list;
	}
}
