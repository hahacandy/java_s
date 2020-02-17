package joinexam.main;

import java.util.Scanner;

import joinexam.ui.JoinView;

public class JoinMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		boolean escape = false;
		
		JoinView joinView = new JoinView();
		
		while(!escape) {
			System.out.println("회원 관리 프로그램");
			System.out.println("1: 회원 목록 보기");
			System.out.println("2: 회원 정보 등록");
			System.out.println("3: 로그인");
			System.out.println("4: 회원 정보 검색");
			System.out.println("5: 회원 정보 수정");
			System.out.println("6: 회원 정보 삭제");
			System.out.println("7: 프로그램 종료");
			
			System.out.print("메뉴 선택:");
			
			int sel = scn.nextInt();
			
			if(sel == 1) {
				joinView.viewAllMember();
			}else if(sel == 2) {
				joinView.regMember(scn);
			}else if(sel == 3) {
				joinView.login(scn);
			}else if(sel == 4) {
				joinView.searchMember(scn);
			}else if(sel == 5) {
				joinView.modifyMember(scn);
			}else if(sel == 6) {
				joinView.removeMember(scn);
			}else if(sel == 7) {
				if(joinView.isYes(scn, "프로그램을 종료")){
					 escape = true;
					 System.out.println("프로그램 종료");
				}
			}

		}
		
	}
	


}
