package exam;

import java.util.*;

import view.UI;

public class ExamMain {

	public static void main(String[] args) {
		UI ui = new UI();
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		boolean escape = false;
		
		while(!escape) {
			System.out.println("1: 목록(List)");
			System.out.println("2: 등록(Insert)");
			System.out.println("3: 검색(Search)");
			System.out.println("4: 수정(Update)");
			System.out.println("5: 삭제(Delete)");
			System.out.println("6: 종료(Exit)");
			
			int menu = 0;
			
			while(true) {
				try {
					System.out.print("메뉴 선택:");
					menu = scn.nextInt();
					if(menu >= 1 && menu <= 6)
						break;
				} catch (Exception e) {}	
				System.out.println("메뉴 선택 오류. 다시 선택!");
			}
			
			
			switch (menu) {
			case 1:
				ui.getDeptList();
				break;
			case 2:
				ui.insertDept(scn);
				break;
			case 3:
				ui.searchDept(scn);
				//부서 번호를 입력시 해당 부서 정보만 출력
				break;
			case 4:
				ui.modifyDept(scn);
				//부서번호를 이용해서  부서명, 지역명 수정
				break;
			case 5:
				ui.removeDept(scn);
				//부서번호를 이용한 삭제
				break;
			case 6:
				escape =true;
				break;
			default:
				break;
			}
			
		}
		
		System.out.println();
		System.out.println("종료됨");
	}

}
