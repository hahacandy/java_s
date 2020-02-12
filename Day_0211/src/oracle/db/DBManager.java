package oracle.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
	
	private DBManager() {}
	
	private static DBManager instance = new DBManager();
	
	public static DBManager getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws Exception{
		Connection conn = null;
		String myDriver = "oracle.jdbc.driver.OracleDriver";
		String myUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String myID = "hr";
		String myPW = "1234";
		try {
			Class.forName(myDriver);
			conn = DriverManager.getConnection(myUrl, myID, myPW);
			System.out.println("드라이버 로딩 & 커넥션 연결 확인");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
}
