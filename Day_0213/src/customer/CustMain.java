package customer;

import java.util.Scanner;

import customer.view.UI;



public class CustMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UI ui = new UI();
		Scanner scn = new Scanner(System.in);
		boolean escape = false;
		
		while(!escape) {
			System.out.println("1: 고객 전체 목록");
			System.out.println("2: 신규 고객 등록");
			System.out.println("3: 고객 검색");
			System.out.println("4: 고객 정보 수정");
			System.out.println("5: 고객 정보 삭제");
			System.out.println("6: 프로그램 종료");
			System.out.print("메뉴 선택:");
			
			
			int sel = scn.nextInt();
			if(sel == 1) { //고객 전체 목록
				ui.viewCusList();
			}else if(sel == 2) { //신규 고객 등록
				ui.regCus(scn);
			}else if(sel == 3) { //고객 검색
				ui.searchCus(scn);
			}else if(sel == 4) { //고객 정보 수정
				ui.modifyCus(scn);
			}else if(sel == 5) { //고객 정보 삭제
				ui.removeCus(scn);
			}else if(sel == 6) {
				System.out.println("프로그램 종료");
				escape = true;
			}
		}
	}

}
