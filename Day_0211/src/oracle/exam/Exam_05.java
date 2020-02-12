package oracle.exam;

import java.util.Scanner;

/*	포함 연산자
 * 	성씨가 '김'
 * 	like '김' 김 1글자만 검색
 *  like '%김' ㅇㄴㄹㄴㅇㄹ김 검색
 *  like '김%'	김ㄴㅇㄻ 검색
 * 	like '%김%' ㄴㅁㅇㄹ김ㅁㄴㅇㄹ 검색
 * 	like '김__' 김xx 만 검색 3글자
 * 
 */

public class Exam_05 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		OracleSQL oracleSQL = new OracleSQL();
		
		System.out.print("이름입력:");
		String name = scn.next();
		
		try {
			oracleSQL.makeConn();
			oracleSQL.printNameSearch(name);
			oracleSQL.takeDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
