import java.sql.*;
import java.util.Scanner;

import db.Oracle2;

/*
 * 키보드로 부서번호를 입력해서 해당 인원 전부 출력
 */

public class Exam_06_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Oracle2 db = new Oracle2();

		Scanner scn = new Scanner(System.in);
		System.out.print("부서번호:");
		int qDno = scn.nextInt();
		scn.close();
		
		try {
			String query = "SELECT * FROM employee where dno=?"; //?자리에 값이 들어간다고 정의함
			ResultSet rs = db.select(query, "int",qDno);

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
			
			System.out.println("검색 종료");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//db객체 제거
		db = null;
	}

}
