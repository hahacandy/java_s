package joinexam.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import joinexam.dao.JoinDAO;
import joinexam.vo.JoinVO;

public class JoinView {

	//멤버 삭제
	public void removeMember(Scanner scn) {
		JoinDAO dao = new JoinDAO();
		JoinVO joinVO = null;
		int row = 0;
		
		String userid = null;
		
		System.out.print("삭제할 id:");
		userid = scn.next();
		clearInput(scn);
		
		try {
			joinVO = dao.search("id", userid).get(0);
		} catch (Exception e) {}
		
		if(joinVO != null) {
			System.out.println("아이디:" + userid);
			System.out.println("이름:" + joinVO.getName());
			System.out.println("이메일:" + joinVO.getEmail());
			
			if(isYes(scn, userid + "님의 정보를 삭제할까요?")) {
				row = dao.deleteMember(userid);
				printUpdate(row, userid + "님의 정보가 삭제되었습니다.", "삭제가 실패하였습니다.");
			}
			
		}else {
			System.out.println("해당 회원정보가 존재하지 않습니다.");

		}
		
		if(isYes(scn, "다시 삭제 하시겠습니까? ")){
			removeMember(scn);
		}
		
	}
	
	//멤버 수정
	public void modifyMember(Scanner scn) {
		JoinDAO dao = new JoinDAO();
		List<String> info = new ArrayList<String>();
		int row = 0;

		String userid = null;
		JoinVO joinVO = null;
		String name = null;
		String passwd = null;
		String passwd2 = null;
		String email = null;
		int age = 0;
		

		System.out.print("id 검색:");
		userid = scn.next();
		clearInput(scn);
		
		joinVO = dao.search("id", userid).get(0);
		
		if(joinVO != null) {
			System.out.println("ID:" + joinVO.getUserid() + " -> 변경불가");
			
			//이름 입력
			System.out.print("이름(" + joinVO.getName() + "):");
			name = scn.next();
			clearInput(scn);
			
			
			//비밀번호 입력
			while(true) {
				System.out.print("비번(");
				for(int i=0; i<joinVO.getPasswd().length(); i++) {
					System.out.print("*");
				}
				System.out.print("):");
				passwd = scn.next();
				clearInput(scn);
				System.out.print("비밀번호 재입력:");
				passwd2 = scn.next();
				clearInput(scn);
				if(passwd.equals(passwd2)) {
					break;
				}else {
					System.out.println("비밀번호가 서로 맞지않습니다. 다시 입력해주세요.");
				}
			}
			
			//이메일 입력
			while(true) {
				System.out.print("이메일(" + joinVO.getEmail() + "):");
				email = scn.next();
				clearInput(scn);
				if(email.matches("[a-zA-Z0-9]*@[a-zA-Z0-9]*\\.[a-zA-Z0-9]{2,3}")) {
					break;
				}else {
					System.out.println("이메일 형식에 맞지않습니다. 다시 입력해주세요.");
				}
			}
			
			//나이 입력
			System.out.print("나이(" + joinVO.getAge() + "):");
			age = scn.nextInt();
			clearInput(scn);
			
			
			//입력된 값을 가공
			info.add("str," + name);
			info.add("str," + passwd);
			info.add("str," + email);
			info.add("int," + age);

			row = dao.updateMember(userid, info);
			printUpdate(row, "회원정보가 수정되었습니다.", "회원정보 수정에 실패하였습니다.");

		
		}else {
			System.out.println("회원정보가 존재하지 않습니다.");
		}
		
		
		if(isYes(scn, "다시 수정 하시겠습니까? ")){
			modifyMember(scn);
		}

	}
	
	//멤버 검색
	public void searchMember(Scanner scn) {
		JoinDAO dao = new JoinDAO();
		
		int sel = 0;
		String mode = null;
		String value = null;
		
		List<JoinVO> joinVOs = new ArrayList<JoinVO>();
		
		while(true) {
			
			System.out.print("ID(1), 이름(2) 검색:");
			sel = scn.nextInt();
			clearInput(scn);
			if(sel == 1) { //id로 검색
				mode = "id";
				System.out.print("id 입력:");
			}else if(sel == 2) { //이름으로 검색
				mode = "name";
				System.out.print("id 이름:");
			}else {
				continue;
			}
			value = scn.next();
			clearInput(scn);
			joinVOs = dao.search(mode, value);
			break;
		}
		
		printMember(joinVOs);
		
		if(isYes(scn, "계속 검색 하시겠습니까?")) {
			searchMember(scn);
		}
		
	}
	
	
	//로그인
	public void login(Scanner scn) {
		JoinDAO dao = new JoinDAO();
		int failCnt = 0;
		String userid = null;
		String passwd = null;
		String[] name = new String[1];

		while(failCnt < 3) {
			System.out.print("ID:");
			userid = scn.next();
			clearInput(scn);
			
			System.out.print("비밀번호:");
			passwd = scn.next();
			clearInput(scn);
			
			if(dao.login(userid, passwd, name)) {
				System.out.println(name[0] + "님 로그인 성공");
			}else {
				System.out.println("로그인 실패");
				failCnt++;
			}
		}
		
		if(failCnt > 3)
			System.out.println("로그인 3회 이상 실패해서 메인으로 돌아갑니다.");
	}
	
	//회원가입
	public void regMember(Scanner scn) {
		JoinDAO dao = new JoinDAO();
		String userid = null;
		String name = null;
		String passwd = null;
		String passwd2 = null;
		String email = null;
		int age = 0;
		
		List<String> regInfo = new ArrayList<String>();
		
		System.out.println("회원 정보 등록");
		//아이디 중복 검사
		while(true) {
			System.out.print("ID:");
			userid = scn.next();
			scn.nextLine(); //남은 입력값 버리기
			if(!dao.isId(userid)) {
				
				break;
			}else {
				System.out.println("중복되는 아이디 입니다. 다시 입력해주세요.");
			}
		}
		
		//이름 입력
		System.out.print("이름:");
		name = scn.next();
		scn.nextLine(); //남은 입력값 버리기
		
		//비밀번호 입력
		while(true) {
			System.out.print("비밀번호:");
			passwd = scn.next();
			scn.nextLine(); //남은 입력값 버리기
			System.out.print("비밀번호 재입력:");
			passwd2 = scn.next();
			scn.nextLine(); //남은 입력값 버리기
			if(passwd.equals(passwd2)) {
				break;
			}else {
				System.out.println("비밀번호가 서로 맞지않습니다. 다시 입력해주세요.");
			}
		}
		
		//이메일 입력
		while(true) {
			System.out.print("이메일:");
			email = scn.next();
			scn.nextLine(); //남은 입력값 버리기
			if(email.matches("[a-zA-Z0-9]*@[a-zA-Z0-9]*\\.[a-zA-Z0-9]{2,3}")) {
				break;
			}else {
				System.out.println("이메일 형식에 맞지않습니다. 다시 입력해주세요.");
			}
		}
		
		//나이 입력
		System.out.print("나이:");
		age = scn.nextInt();
		
		if(isYes(scn, "등록 하시겠습니까?")) {
			//회원 가입 할 정보들을 넣고 회원가입 시작
			regInfo.add("str,"+userid);
			regInfo.add("str,"+name);
			regInfo.add("str,"+passwd);
			regInfo.add("str,"+email);
			regInfo.add("int,"+age);
			
			int row  = dao.regMember(regInfo);
			
			printUpdate(row, "회원가입에 성공했습니다.", "회원가입에 실패했습니다.");
		}
		
	}
	
	//insert delete update 실행 후 결과 출력
	private void printUpdate(int row, String kind, String kind2) {
		if(row > 0)
			System.out.println(kind);
		else
			System.out.println(kind2);
	}
	
	//전체 멤버 리스트 가져오기
	public void viewAllMember() {
		List<JoinVO> joinVOs = new JoinDAO().selectAllJoin();
		
		printMember(joinVOs);
	}
	
	//멤버 리스트 출력 메소드
	private void printMember(List<JoinVO> list) {
		if(list != null) {
			int lenU = 9;
			int lenN = 7;
			int lenP = 9;
			int lenE = 8;
			int lenA = 5;
			int lenR = 10;
			
			for(JoinVO vo : list) {
				if(lenU < vo.getUserid().length())
					lenU = vo.getUserid().length();
				
				if(lenN < vo.getName().length())
					lenN = vo.getName().length();
				
				if(lenP < vo.getPasswd().length())
					lenP = vo.getPasswd().length();
				
				if(lenE < vo.getEmail().length())
					lenE = vo.getEmail().length();
				
				if(lenA < String.valueOf(vo.getAge()).length())
					lenA = String.valueOf(vo.getAge()).length();
				
				if(lenR < vo.getRegdate().length())
					lenR = vo.getRegdate().length();
			}
			

			lenU += 3;
			lenN += 3;
			lenP += 3;
			lenE += 3;
			lenA += 3;
			lenR += 3;
			
			
			System.out.println();
			System.out.printf("%-"+lenU+"s%-"+lenN+"s%-"+lenP+"s%-"+lenE+"s%-"+lenA+"s%-"+lenR+"s%n",
					"UserID", "Name", "Passwd", "Email", "Age", "RegDate");
			for(JoinVO vo: list) {
				System.out.printf("%-"+lenU+"s", vo.getUserid());
				System.out.printf("%-"+lenN+"s", vo.getName());
				System.out.printf("%-"+lenP+"s", vo.getPasswd());
				System.out.printf("%-"+lenE+"s", vo.getEmail());
				System.out.printf("%-"+lenA+"d", vo.getAge());
				System.out.printf("%-"+lenR+"s", vo.getRegdate());
				System.out.println();
			}
			System.out.println();
		}else {
			System.out.println();
			System.out.println("등록된 회원 정보 없음");
			System.out.println();
		}
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
	
	//입력값 청소
	private void clearInput(Scanner scn) {
		scn.nextLine();
	}
	
	
	
}
