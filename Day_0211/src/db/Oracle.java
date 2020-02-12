package db;

import java.sql.*;

public class Oracle {
	
	public static Connection getConnection() {
		
		// 1단계 JDBC 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("오라클 드라이버 로딩 성공");
		} catch (Exception e) {
			System.out.println("오라클 드라이버 로딩 실패");
			e.printStackTrace();
		}
		
		//2단계 Connection 객체 생성 - 커넥션 연결
		String myURL = "jdbc:oracle:thin:@localhost:1521/xe"; //1521 오라클 연결 포트
		String myID = "hr";
		String myPW = "1234";
		Connection con = null;
		try {
			con = DriverManager.getConnection(myURL, myID, myPW);
			System.out.println("db 연결 성공");
			return con;
		}catch(Exception e){
			System.out.println("db 연결 실패");
			System.out.println(e);
			return null;
		}
		
	}
	
	public static void Disconnect(Connection con, Statement stmt, ResultSet rs) {
		
		//4단계 닫기(닫지않으면 해킹의 위험이 존재함)
		try {
			rs.close();
			stmt.close();
			con.close();	
			System.out.println("db 연결 종료");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return;
	}
	
	public static void Disconnect(Connection con, PreparedStatement pstmt, ResultSet rs) {
		
		//4단계 닫기(닫지않으면 해킹의 위험이 존재함)
		try {
			rs.close();
			pstmt.close();
			con.close();	
			System.out.println("db 연결 종료");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return;
	}

}
