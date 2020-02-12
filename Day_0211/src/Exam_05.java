import java.sql.*;
import java.util.Scanner;

/*
 * 키보드로 사원번호를 입력 검색
 */

public class Exam_05 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = db.Oracle.getConnection();
//		Statement stmt = null;
		PreparedStatement pstmt = null; //재사용가능
		ResultSet rs = null;
		Scanner scn = new Scanner(System.in);
		System.out.print("사원이름:");
		String name = scn.next();
		
		try {
//			stmt = con.createStatement(); //쿼리 또 날리려면 또 적어야함
//			String query = "SELECT * FROM employee where ename='" + name + "'";
			String query = "SELECT * FROM employee where ename=?"; //?자리에 값이 들어간다고 정의함
			pstmt = con.prepareStatement(query); //재사용 가능, 쿼리도 같이 넣어줌
			pstmt.setString(1, name); //쿼리의 1번째 물움표에다가 name의 변수를 넣음
//			pstmt.setInt(2, 7788); //인트도 가능
//			rs = stmt.executeQuery(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int eno = rs.getInt("eno"); // "rs.getInt("eno")"
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				int manager = rs.getInt("manager");
				String hireDate = rs.getString("hiredate");
				double salary = rs.getDouble("salary");
				double commission = rs.getDouble("commission");
				int dno = rs.getInt("dno");
				
				System.out.print(eno + "\t");
				System.out.print(ename + "\t");
				System.out.print(job + "\t");
				System.out.print(manager + "\t");
				System.out.print(hireDate + "\t");
				System.out.print(salary + "\t");
				System.out.print(commission + "\t");
				System.out.println(dno);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		db.Oracle.Disconnect(con, pstmt, rs);
	}

}
