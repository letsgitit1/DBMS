package UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//db연결
public class DBConnecter {
	public static Connection getConnection() {
		Connection conn=null;
		
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="PROJECT";
		String pw="1234";
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,user,pw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();	
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
