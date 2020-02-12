package exam2;

import model.DDLDao;

/*	DDL(데이터 정의어): CREATE, ALTER, DROP
 * 	DML(데이터 조작어):	CRUD(C:INSERT, R:SELECT, U:UPDATE, D:DELETE)
 * 	DCL(데이터 제어어): GRANT(권한을 줄떄), REVOKE(권한을 준걸 회수 할 때)
 * 	TCL(트랜젝션제어): COMMIT, ROLLBACK, SAVEPOINT
 * 	CREATE DATABASE AA; -> 오라클은 이런거 없음, 걍 1개임 데이터베이스
 * 	INSERT INTO AAA(dno, dname, regdate, score) <-- 1:1매칭
 * 		VALUES(10, 'AAA', '20200212', 90) <- 날짜는 홀따움표 해도되고 안해도되고
 * 
 * 	INSERT INTO AAA
 * 		VALUES(10, 'AAA', '20200212', 90) <- 순서대로 들어가니 주의, 그리고 값을 전부 다 적어야함
 * 
 */
public class Exam_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DDLDao dao = new DDLDao();
//		dao.createTable(); //테이블 생성
		int dno = 10;
		String dname = "홍길동";
		String date = "20200212";
		int score = 90;
		
		
		int row = dao.insertAAA(dno, dname, date, score);
		
		if(row == 0)
			System.out.print("db insert 실패");
		else
			System.out.print(row + "개의 insert 성공");

	}

}
