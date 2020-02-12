package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBManager;

public class DDLDao {
	DBManager manager = DBManager.getInstance();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//테이블 생성메소드 정의
	public int createTable() {
		StringBuffer query = new StringBuffer();
		query.append("CREATE TABLE AAA(");
		query.append("dno NUMBER(2),");
		query.append("dname VARCHAR2(9) NOT NULL,");
		query.append("regdate DATE,");
		query.append("score number(3),");
		query.append("PRIMARY KEY(dno))");
		
		int row=0;
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			row = pstmt.executeUpdate(); //정상적으로 수행되면 등록이 성공한 갯수가 들어감, 실패하면 0
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return row;
		
	}
	
	//등록메소드 정의
	public int insertAAA(int dno, String ename, String date, int score) {
		int row = 0;
		
		String query = "INSERT INTO AAA VALUES(?,?,?,?)";
		
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dno);
			pstmt.setString(2, ename);
			pstmt.setString(3, date);
			pstmt.setInt(4, score);
			row = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return row;
	}
}
