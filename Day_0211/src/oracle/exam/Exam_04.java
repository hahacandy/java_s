package oracle.exam;

import java.sql.*;
import java.util.Scanner;

import db.Oracle;

/*	키보드로 날자입력 (예 1901-01-02)
 * 	사원정보테이블에서
 * 
 */

public class Exam_04 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		OracleSQL oracleSQL = new OracleSQL();
		
		System.out.print("날짜 입력:");
		String date = scn.next();
		
		try {
			oracleSQL.makeConn();
			oracleSQL.printDateSearchInEmployee(date);
			oracleSQL.cleanUp();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
