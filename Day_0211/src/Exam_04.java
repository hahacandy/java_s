import java.sql.*;
import java.util.Scanner;

/*
 * 키보드로 사원번호를 입력 검색
 */

public class Exam_04 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = db.Oracle.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		Scanner scn = new Scanner(System.in);
		System.out.print("사원이름:");
		String name = scn.next();
		
		try {
			stmt = con.createStatement(); //쿼리 또 날리려면 또 적어야함
			String query = "SELECT * FROM employee where ename='" + name + "'";
			rs = stmt.executeQuery(query);
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
		
		db.Oracle.Disconnect(con, stmt, rs);
	}

}
