package oracle.db;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class OracleConn {
	private String myDriver = "oracle.jdbc.driver.OracleDriver";
	private String myUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	private String myID = "hr";
	private String myPW = "1234";
	
	protected Connection myConn;
	
	public OracleConn() {}
	
	public void makeConn() throws Exception{
		Class.forName(myDriver);
		myConn = DriverManager.getConnection(myUrl, myID, myPW);
		System.out.println("드라이버로딩 & 연결 성공");
	}
	
	public abstract void cleanUp() throws Exception; //커넥션전에 닫ㄷ아야할것
	
	public void takeDown() throws Exception{
		cleanUp();
		myConn.close();
		System.out.println("db연결 종료");
	}
}
