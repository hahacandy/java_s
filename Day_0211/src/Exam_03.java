import java.sql.*;


public class Exam_03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = db.Oracle.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM employee where eno=7788");
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
