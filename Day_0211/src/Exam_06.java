import java.sql.*;
import java.util.Scanner;

/*
 * 키보드로 부서번호를 입력해서 해당 인원 전부 출력
 */

public class Exam_06 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = db.Oracle.getConnection();
		PreparedStatement pstmt = null; //재사용가능
		ResultSet rs = null;
		Scanner scn = new Scanner(System.in);
		System.out.print("부서번호:");
		int qDno = scn.nextInt();
		scn.close();
		
		try {
			String query = "SELECT * FROM employee where dno=?"; //?자리에 값이 들어간다고 정의함
			pstmt = con.prepareStatement(query); //재사용 가능, 쿼리도 같이 넣어줌
//			pstmt.setString(1, name); //스트링 쿼리
			pstmt.setInt(1, qDno); //인트 쿼
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
