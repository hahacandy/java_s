import java.sql.Connection;
import java.sql.ResultSet;

import db.Oracle;


/*	1. DB연동
 * 	2. 검색
 * 
 * 
 */
public class Exam_01 {

	public static void main(String[] args) {

		//db 연결
		Connection con = db.Oracle.getConnection();
		
		//3단계 SQL 사용
		java.sql.Statement stmt = null; //명령문을 실행하고 결과를 resultSet이 받음
		ResultSet rs = null; //일종의 컬렉션 같은 역할, select를 쓸땐 이게 필요함
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM department"); //실제 쿼리를 db로 쏘고 결과를 rs에 담음
			while(rs.next()) {
				System.out.print(rs.getInt("dno") + "\t");
				System.out.print(rs.getString("DNAME") + "\t");
				System.out.print(rs.getString("LOC") + "\t");
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		//db닫기
		db.Oracle.Disconnect(con, stmt, rs);
		
		
	}

}
