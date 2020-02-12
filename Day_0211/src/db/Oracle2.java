package db;

import java.sql.*;

public class Oracle2 {
	
	public Connection con = null;
	
	public Oracle2() {
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
			con = null;
			try {
				con = DriverManager.getConnection(myURL, myID, myPW);
				System.out.println("db 연결 성공");
			}catch(Exception e){
				System.out.println("db 연결 실패");
				System.out.println(e);
			}
	}
	
	public ResultSet select(String query, String type1, Object var1) {
		
		PreparedStatement pstmt = null; //재사용가능
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(query); //재사용 가능, 쿼리도 같이 넣어줌
			if(type1.equalsIgnoreCase("int"))
			{
				pstmt.setInt(1, (int)var1); //스트링 쿼리
			}
			else if(type1.equalsIgnoreCase("str"))
			{
				pstmt.setString(1, (String)var1); //스트링 쿼리
			}
			
			rs = pstmt.executeQuery();
			return rs;
		} catch (Exception e) {
			System.out.print("값 가져오기 실패");
			return null;
		}

	}
	
	public ResultSet select(String query, String type1, Object var1, String type2, Object var2) {
		
		PreparedStatement pstmt = null; //재사용가능
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(query); //재사용 가능, 쿼리도 같이 넣어줌
			
			if(type1.equalsIgnoreCase("int"))
			{
				pstmt.setInt(1, (int)var1); //스트링 쿼리
			}
			else if(type1.equalsIgnoreCase("str"))
			{
				pstmt.setString(1, (String)var1); //스트링 쿼리
			}
			
			if(type2.equalsIgnoreCase("int"))
			{
				pstmt.setInt(2, (int)var1); //스트링 쿼리
			}
			else if(type2.equalsIgnoreCase("str"))
			{
				pstmt.setString(2, (String)var1); //스트링 쿼리
			}
			
			rs = pstmt.executeQuery();
			return rs;
		} catch (Exception e) {
			System.out.print("값 가져오기 실패");
			return null;
		}

	}
	

	

}
