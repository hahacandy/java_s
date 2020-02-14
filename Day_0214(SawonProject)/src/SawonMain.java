import java.util.Scanner;

import view.UI;

public class SawonMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		boolean escape = false;
		UI ui = new UI();
		
		
		while(!escape) {
			System.out.println("사원&부서관리 프로그램");
			System.out.println();
			System.out.println("1:사원 전체 목록");
			System.out.println("2:신규 사원 등록");
			System.out.println("3:사원 검색");
			System.out.println("4:사원 정보 수정");
			System.out.println("5:사원 정보 삭제");
			System.out.println("6:프로그램 종료");
			
			System.out.print("메뉴 입력:");
			int sel = scn.nextInt();
			
			if(sel >= 1 && sel <= 6) {
				if(sel==1) { //전체목록
					ui.getAllSawonList();
				}else if(sel==2) { //신규 등록
					ui.newRegi(scn);
				}else if(sel==3) { //사원 검색
					ui.searchSawon(scn);
				}else if(sel==4) { //사원 정보 수정
					ui.modifySawon(scn);
				}else if(sel==5) { //사원 정보 삭제
					ui.removeSawon(scn);
				}else if(sel==6) { //종료
					escape = true;
					System.out.println("프로그램 종료");
				}
			}
			
		}
		
	}

}
