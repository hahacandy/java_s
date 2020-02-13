package customer.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
	private DBManager() {}//이렇게하면 외부에서 new로 객체 생성 못함
	
	//런타임 실행시 instance변수를 만들고 거기에는 new DBManager()(자기자신)의 객체 생성
	private static DBManager instance = new DBManager();
	
	//외부에서는 하나의 동일한 객체만 끌어다 쓸 수 있게
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
//			System.out.println("db연결 성공");
		}catch (Exception e) {
			System.out.println("db연결 실패");
		}
		
		return conn;
	}

}
