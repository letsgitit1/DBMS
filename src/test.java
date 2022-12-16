import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import UserDao.H_MEMBER_DAO;
import UserVo.H_MEMBER_VO;

public class test {

	public static void main(String[] args) throws SQLException {
			H_MEMBER_DAO dao=H_MEMBER_DAO.getMemberDao();
			H_MEMBER_VO vo=new H_MEMBER_VO(0, 
					1, 
					"먕먕", 
					"1234", 
					"1234", 
					"010-1234-1234", 
					"M", 
					"1970-09-01");
			if(dao.insert(vo)) { System.out.println("데이터 삽입 성공");}
			else {System.out.println("데이터 삽입 실패");}
			}
	}

