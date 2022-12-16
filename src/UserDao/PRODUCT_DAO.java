package UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import UserVo.PRODUCT_VO;

public class PRODUCT_DAO {
	Connection conn=null;
	PreparedStatement pstm=null;
	ResultSet rs= null;
	private static PRODUCT_DAO dao= new PRODUCT_DAO();
	private PRODUCT_DAO() {};
	public static PRODUCT_DAO getPRODUCT_DAO() {return dao;}
	
	//상품 정보 출력
	public List<PRODUCT_VO>productInfo() throws SQLException{
		List<PRODUCT_VO> list= new ArrayList<>();
		PRODUCT_VO product=null;
		conn=DBConnecter.getConnection();
		String sql="SELECT * FROM PRODUCT";
		pstm=conn.prepareStatement(sql);
		rs=pstm.executeQuery();
		
		while(rs.next()) {
			product=new PRODUCT_VO(
					rs.getInt(1), 
					rs.getString(2),
					rs.getInt(3),
					rs.getInt(4));
			list.add(product);
		}
		if(rs!=null) {rs.close();}
		if(pstm!=null) {rs.close();}
		if(conn!=null) {rs.close();}
		
		return list;
	}//productInfoEND
	//상품 번호로 상품 기간 추출하기
	public int peirod_extract(int id) throws SQLException {
		conn=DBConnecter.getConnection();
		String query="SELECT PERIOD FROM PRODUCT\r\n"
				+ "		WHERE PRODUCT_NO=?";
		pstm=conn.prepareStatement(query);
		pstm.setInt(1, id);
		rs=pstm.executeQuery();
		rs.next();
		return 	rs.getInt(1);
	}
	//상품 번호에 해당되는 상품 가져오기
public PRODUCT_VO SelectOneProduct(int Pno) throws SQLException {	
	PRODUCT_VO vo=null;
	conn=DBConnecter.getConnection();
	String query="SELECT * FROM PRODUCT where PRODUCT_NO=?";
	pstm=conn.prepareStatement(query);
	pstm.setInt(1, Pno);
	rs=pstm.executeQuery();
	if(rs.next()) {
		vo=new PRODUCT_VO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
	}
	if(rs!=null) {rs.close();}
	if(pstm!=null) {pstm.close();}
	if(conn!=null) {conn.close();}
	return vo;
}
}//classEND
