package shopping.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import shopping.dao.ShoppingDao;
import shopping.vo.ShoppingVO;

public class ShoppingUI extends ShoppingUIManager{
	ShoppingDao dao = new ShoppingDao();
	
	public void start(Scanner scn) {
		int sel = 0;


		while(true) {
			System.out.println("쇼핑몰 회원 관리 프로그램");
			System.out.println("[1] 회원 정보 등록");
			System.out.println("[2] 회원정보현황 조회");
			System.out.println("[3] 회원 정보 수정");
			System.out.println("[4] 회원 정보 삭제");
			System.out.println("[5] 프로그램 종료");
			System.out.print("메뉴를 선택하세요(1~4):");
			sel = scn.nextInt();
			
			if(sel==1) {
				regMember(scn);
			}else if(sel==2) {
				viewAllMember();
			}else if(sel==3) {
				modiMember(scn);
			}else if(sel==4) {
				deleteMember(scn);
			}else if(sel==5) {
				if(isYes(scn, "프로그램을 종료할까요?")) {
					break;
				}
			}
		}

	}
	
	//[4] 회원 정보 삭제
	private void deleteMember(Scanner scn) {
		List<ShoppingVO> list = null;
		
		int row=0;
		
		int custno=0;
		
		//회원번호 입력
		while(true) {
			System.out.print("회원번호:");
			try {
				custno = scn.nextInt();
				scn.nextLine();
				if(custno >= 100000 && custno <= 999999)
					break;
				else
					System.out.println("회원번호는 정수 6자리 입니다");
			} catch (Exception e) {}
		}
		
		list = dao.getMember(custno);
		if(printMember(list)) {
			if(isYes(scn, "정말로 삭제할까요?")) {
				row = dao.deleteMember(custno);
				printUpdate(row, "회원삭제를 완료하였습니다.", "회원삭제를 실패하였습니다.");
			}
		}
		
	}
	
	//[3] 회원 정보 수정
	private void modiMember(Scanner scn) {
		List<ShoppingVO> list = null;
		List<Object> iInfos = new ArrayList<Object>();
		List<String> infos = null;
		
		int row=0;
		
		int custno=0;
		String custName=null;
		String phone=null;
		String gender=null;
		String grade=null;
		
		//회원번호 입력
		while(true) {
			System.out.print("회원번호:");
			try {
				custno = scn.nextInt();
				scn.nextLine();
				if(custno >= 100000 && custno <= 999999)
					break;
				else
					System.out.println("회원번호는 정수 6자리 입니다");
			} catch (Exception e) {}
		}
		
		list = dao.getMember(custno);
		if(printMember(list)) {
			//회원 이름 입력
			while(true) {
				System.out.print("변경하려는 회원이름:");
				try {
					custName = scn.nextLine();
					break;
				} catch (Exception e) {}
			}
			
			//연락처 입력
			while(true) {
				System.out.print("변경하려는 연락처:");
				try {
					phone = scn.nextLine();
					break;
				} catch (Exception e) {}
			}
			
			//성별 입력
			while(true) {
				System.out.print("변경하려는 성별(M/F):");
				try {
					gender = scn.nextLine().toUpperCase();
					if(gender.matches("(^M)|(^F)") && gender.length() == 1 || gender.length() == 0)
						break;
					else
						System.out.println("성별 입력 오류!");
				} catch (Exception e) {}
			}
			
			//회원등급 입력
			while(true) {
				System.out.print("변경하려는 회원등급(A/B/C):");
				try {
					grade = scn.nextLine().toUpperCase();
					if(grade.matches("(^A)|(^B)|(^C)") && grade.length() == 1 || grade.length() == 0)
						break;
					else
						System.out.println("회원등급 입력 오류!");
				} catch (Exception e) {}
			}
			
			if(isYes(scn, "회원정보를 수정할까요?")) {
				
				if(custName.length() > 0)
					iInfos.add(custName);
				else
					iInfos.add(list.get(0).getCustNo());
				
				if(phone.length() > 0)
					iInfos.add(phone);
				else
					iInfos.add(list.get(0).getPhone());
				
				if(gender.length() > 0)
					iInfos.add(gender);
				else
					iInfos.add(list.get(0).getGender());
				
				if(grade.length() > 0)
					iInfos.add(grade);
				else
					iInfos.add(list.get(0).getGrade());
				
				infos = inputListInfos(iInfos);
				
				row = dao.modiMember(custno, infos);
				printUpdate(row, "회원정보 수정 완료", "회원정보 수정 실패");
				
			}
		}
		

		
		
	}
	
	
	//[2] 회원정보현황 조회
	private void viewAllMember() {
		List<ShoppingVO> list = dao.getAllMember();
		printMember(list);
	}
	
	
	//[1] 회원 정보 등록
	private void regMember(Scanner scn) {
		List<Object> iInfos = new ArrayList<Object>();
		List<String> infos = null;
		
		int row=0;
		
		int custno=0;
		String custName=null;
		String phone=null;
		String gender=null;
		String grade=null;
		
		//회원번호 입력
		while(true) {
			System.out.print("회원번호:");
			try {
				custno = scn.nextInt();
				if(custno >= 100000 && custno <= 999999)
					break;
				else
					System.out.println("회원번호는 정수 6자리 입니다");
			} catch (Exception e) {}
		}
		
		//회원 이름 입력
		while(true) {
			System.out.print("회원이름:");
			try {
				custName = scn.next();
				break;
			} catch (Exception e) {}
		}
		
		//연락처 입력
		while(true) {
			System.out.print("연락처:");
			try {
				phone = scn.next();
				break;
			} catch (Exception e) {}
		}
		
		//성별 입력
		while(true) {
			System.out.print("성별(M/F):");
			try {
				gender = scn.next().toUpperCase();
				if(gender.matches("(^M)|(^F)") && gender.length() == 1)
					break;
				else
					System.out.println("성별 입력 오류!");
			} catch (Exception e) {}
		}
		
		//회원등급 입력
		while(true) {
			System.out.print("회원등급(A/B/C):");
			try {
				grade = scn.next().toUpperCase();
				if(grade.matches("(^A)|(^B)|(^C)") && grade.length() == 1)
					break;
				else
					System.out.println("회원등급 입력 오류!");
			} catch (Exception e) {}
		}
		
		if(isYes(scn, "회원정보를 등록할까요?")) {
			iInfos.add(custno);
			iInfos.add(custName);
			iInfos.add(phone);
			iInfos.add(gender);
			iInfos.add(grade);
			
			infos = inputListInfos(iInfos);
			
			row = dao.regMemberQuery(infos);
			
			printUpdate(row, "회원정보 등록을 완료했습니다.", "이미 등록된 회원정보 입니다.");
			
		}
		

	}
	
}
