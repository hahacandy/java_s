package oracle.exam;

import java.util.Scanner;

/*	키보드로 부서 번호 입력
 * 	부서번호에 해당하는 사원정보 출력(employee)
 * 	
 */

public class Exam_03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		OracleSQL oracleSQL = new OracleSQL();
		
		System.out.print("부서번호:");
		int dno = scn.nextInt();
		
		try {
			oracleSQL.makeConn();
			oracleSQL.printSearchInEmployee(dno);
			oracleSQL.takeDown();
		}catch (Exception e) {
			System.out.println("db연결 실패");
			e.printStackTrace();
		}	

	}

}
