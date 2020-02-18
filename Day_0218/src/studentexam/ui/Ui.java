package studentexam.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import studentexam.dao.StudentDAO;
import studentexam.vo.StudentVO;

public class Ui {
	StudentDAO dao = new StudentDAO();

	//학생 정보 관리 메뉴
	public void studentInfoMana(Scanner scn) {
		boolean escape = false;
		int sel = 0;
		while(!escape) {
			System.out.println("[1] <학생정보 관리>");
			System.out.println();
			System.out.println("1. 학생정보 입력");
			System.out.println("2. 학생정보 조회(전체)");
			System.out.println("3. 학생정보 검색");
			System.out.println("4. 학생정보 변경");
			System.out.println("5. 학생정보 삭제");
			System.out.println("6. 돌아가기");
			
			System.out.print("선택:");
			sel = scn.nextInt();
			
			if(sel == 1) {
				enterStudentInfo(scn);
			}else if(sel ==2) {
				viewAllStudent();
			}else if(sel ==3) {
				searchStudent(scn);
			}else if(sel ==4) {
				modifyStudent(scn);
			}else if(sel ==5) {
				deleteSutdent(scn);
			}else if(sel ==6) {
				if(isYes(scn, "돌아갈까요?")){
					escape = true;
				}
			}
			
		}
	}
	
	private void deleteSutdent(Scanner scn) {
		int row = 0;
		List<StudentVO> list = null;
		int hakbun = 0;
		
		System.out.print("삭제하려는 학생의 학번:");
		hakbun = scn.nextInt();
		
		list = dao.searchStudents("학번", String.valueOf(hakbun));
		if(printMember(list)) {
			if(isYes(scn, "해당 학생을 정말로 삭제하겠습니까?")) {
				row = dao.deleteStudent(hakbun);
				printUpdate(row, "해당 학생을 삭제하였습니다.", "해당 학생을 삭제하지 못하였습니다.");
			}
		}

	}
	
	private void modifyStudent(Scanner scn) {
		List<StudentVO> list = null;
		List<String> infos = new ArrayList<String>();
		int row = 0;
		
		int hakbun = 0;
		String name = null;
		String addr = null;
		String email = null;
		String birth = null;
		
		System.out.print("변경하려는 학생의 학번:");
		hakbun = scn.nextInt();
		
		list = dao.searchStudents("학번", String.valueOf(hakbun));
		if(printMember(list)) {
		
			System.out.print("변경할 이름:");
			name = scn.next();
			
			System.out.print("변경할 주소:");
			addr = scn.next();
			
			//이메일 입력
			while(true) {
				System.out.print("변경할 이메일:");
				email = scn.next();
				
				if(email.matches("[a-zA-Z0-9]*@[a-zA-Z0-9]*\\.[a-zA-Z0-9]{2,3}")) {
					break;
				}else {
					System.out.println("이메일 형식에 맞지않습니다. 다시 입력해주세요.");
				}
			}
			
			//생년월일
			while(true) {
				System.out.print("변경할 생년월일(xxxx-xx-xx):");
				birth = scn.next();
				
				if(birth.matches("(19[0-9]{2}|20[0-9]{2})-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])")) {
					break;
				}else {
					System.out.println("생년월일 형식에 맞지않습니다. 다시 입력해주세요.");
				}
			}
			
			if(isYes(scn, "수정 할까요?")) {

					infos.add("str," + name);
					infos.add("str," + addr);
					infos.add("str," + email);
					infos.add("str," + birth);
					
					row = dao.modifyStudent(hakbun, infos);
					printUpdate(row, name + " 학생정보가 수정이 완료됨.", name + " 학생정보가 수정이 실패함.");
			}
			
		}

	}
	
	
	//이름 학번 학년 으로 학생 검색
	private void searchStudent(Scanner scn) {
		int sel = 0;
		boolean escape = false;
		
		while(!escape) {
			System.out.println("[1-3] <학생정보 조회>");
			System.out.println();
			
			System.out.println("1. 이름으로 검색");
			System.out.println("2. 학번으로 검색");
			System.out.println("3. 학년으로 검색");
			System.out.println("4. 검색 취소");
			
			System.out.print("선택:");
			sel = scn.nextInt();
			
			if(sel==1) {
				searchStudent2(scn, "1", "이름");
			}else if(sel ==2) {
				searchStudent2(scn, "2", "학번");
			}else if(sel ==3) {
				searchStudent2(scn, "3", "학년");
			}else if(sel ==4) {
				if(isYes(scn, "검색을 취소하시겠습니까?")) {
					escape = true;
				}
			}
		}

	}
	private void searchStudent2(Scanner scn ,String number, String value) {
		String input = null;

		System.out.println("[1-3-" + number + "] " + value + "으로 검색");
		System.out.println();
		System.out.print("학생 " + value + ":");
		
		input = scn.next();
		
		List<StudentVO> list = dao.searchStudents(value, input);
		
		printMember(list);
		
	}
	
	
	//모든 학생 가져와서 출력
	private void viewAllStudent() {
		System.out.println("[1-2] <학생정보 조회>");
		System.out.println();
		
		List<StudentVO> list = dao.getAllStudents();
		
		printMember(list);
	}
	

	//학생정보 입력
	private void enterStudentInfo(Scanner scn) {
		List<String> infos = new ArrayList<String>();
		int row = 0;
		
		int hakbun = 0;
		String name = null;
		String addr = null;
		String email = null;
		String birth = null;
		String birthC = null;
		
		System.out.println("[1-1] <학생정보 입력>");
		System.out.println();
		
		//학번 입력
		while(true) {
			System.out.print("학번:"); 
			hakbun = scn.nextInt();
			if(hakbun == 0000)
				return;
			else if(hakbun < 1000 && hakbun > 10000) {
				System.out.println("학번 최소 4자리 입니다!");
			}else {
				break;
			}
		}

		
		//이름 입력
		System.out.print("이름:");
		name = scn.next();
		
		//주소 입력
		System.out.print("주소:");
		addr = scn.next();
		
		//이메일 입력
		while(true) {
			System.out.print("이메일:");
			email = scn.next();
			if(email.matches("[a-zA-Z0-9]*@[a-zA-Z0-9]*\\.[a-zA-Z0-9]{2,3}")) {
				break;
			}else {
				System.out.println("이메일 형식에 맞지않습니다. 다시 입력해주세요.");
			}
		}
		
		//생년월일
		while(true) {
			System.out.print("생년월일(xxxx-xx-xx):");
			birth = scn.next();
			
			if(birth.matches("(19[0-9]{2}|20[0-9]{2})-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])")) {
				break;
			}else {
				System.out.println("생년월일 형식에 맞지않습니다. 다시 입력해주세요.");
			}
		}
		
		
		
		if(isYes(scn, "등록 할까요?")) {
			
			if(!dao.isCntQuery("student_bun", String.valueOf(hakbun))) {
				infos.add("int," + String.valueOf(hakbun));
				infos.add("str," + name);
				infos.add("str," + addr);
				infos.add("str," + email);
				infos.add("str," + birth);
				
				row = dao.regStudent(infos);
				printUpdate(row, name + " 의 학생정보가 등록되었습니다.", name + " 의 학생정보가 등록실패하였습니다.");
			}else {
				System.out.println("이미 등록된 학생입니다.");
			}
			

			
			
		}
		
	}
	
	//insert delete update 실행 후 결과 출력
	private void printUpdate(int row, String kind, String kind2) {
		System.out.println();
		if(row > 0)
			System.out.println(kind);
		else
			System.out.println(kind2);
		System.out.println();
	}
	
	
	//y눌렀는지 n눌렀는지 확인
	public boolean isYes(Scanner scn, String ask) {
		
		while(true) {
			System.out.println(ask + " (y/n)");
			System.out.print("입력:");
			String sel = scn.next();
			if(sel.equalsIgnoreCase("y")) {
				return true;
			}else if(sel.equalsIgnoreCase("n")) {
				return false;
			}

		}
		
	}
	
	//학생 리스트 출력 메소드
	private boolean printMember(List<StudentVO> list) {
		if(list != null) {
			int len1 = 9;
			int len2 = 7;
			int len3 = 9;
			int len4 = 8;
			int len5 = 5;
			
			int to_len1 = 0;
			int to_len2 = 0;
			int to_len3 = 0;
			int to_len4 = 0;
			int to_len5 = 0;
			
			for(StudentVO vo : list) {
				to_len1 = String.valueOf(vo.getHakbun()).length(); 
				if(len1 < to_len1)
					len1 = to_len1;
				
				to_len2 = vo.getName().length();
				if(len2 < to_len2)
					len2 = to_len2;
				
				to_len3 = vo.getAddr().length();
				if(len3 < to_len3)
					len3 = to_len3;
				
				to_len4 = vo.getEmail().length();
				if(len4 < to_len4)
					len4 = to_len4;
				
				to_len5 = vo.getBirth().length();
				if(len5 < to_len5)
					len5 = to_len5;
				
			}
			
			len1 += 3;
			len2 += 3;
			len3 += 3;
			len4 += 3;
			len5 += 3;
			
			System.out.println("총 " + list.size() + "명의 학생을 조회합니다.");
			System.out.println();
			System.out.printf("%-"+len1+"s%-"+len2+"s%-"+len3+"s%-"+len4+"s%-"+len5+"s%n",
					"HakBun", "Name", "Addr", "Email", "Birth");
			for(StudentVO vo: list) {
				System.out.printf("%-"+len1+"d", vo.getHakbun());
				System.out.printf("%-"+len2+"s", vo.getName());
				System.out.printf("%-"+len3+"s", vo.getAddr());
				System.out.printf("%-"+len4+"s", vo.getEmail());
				System.out.printf("%-"+len5+"s", vo.getBirth());
				System.out.println();
			}
			System.out.println();
			return true;
		}else {
			System.out.println();
			System.out.println("등록된 학생 정보 없음");
			System.out.println();
			return false;
		}
	}
	
}
