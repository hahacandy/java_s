package shopping.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import shopping.vo.ShoppingVO;


public class ShoppingUIManager {

	//insert delete update 실행 후 결과 출력
	protected void printUpdate(int row, String kind, String kind2) {
		System.out.println();
		if(row > 0)
			System.out.println(kind);
		else
			System.out.println(kind2);
		System.out.println();
	}
	
	//리스트에 값 입력기
	protected List<String> inputListInfos(List<Object> IInfos) {
		List<String> infos = new ArrayList<String>();
		for(Object info : IInfos) {
			if(info instanceof String) {
				infos.add("str," + info);
			}else if(info instanceof Integer) {
				infos.add("int," + String.valueOf(info));
			}else {
				System.out.println("리스트 값 입력 오류!");
			}
		}
		return infos;
	}
	
	
	//y눌렀는지 n눌렀는지 확인
	protected boolean isYes(Scanner scn, String ask) {
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
	
	//예약 리스트 출력 메소드
	protected boolean printMember(List<ShoppingVO> list) {
		if(list != null) {
			String len1N = "custno";
			String len2N = "custname";
			String len3N = "phone";
			String len4N = "gender";
			String len5N = "joindate";
			String len6N = "grade";
//			String len7N = "status";
			
			int len1 = len1N.length();
			int len2 = len2N.length();
			int len3 = len3N.length();
			int len4 = len4N.length();
			int len5 = len5N.length();
			int len6 = len6N.length();
//			int len7 = len7N.length();
			
			int to_len1 = 0;
			int to_len2 = 0;
			int to_len3 = 0;
			int to_len4 = 0;
			int to_len5 = 0;
			int to_len6 = 0;
//			int to_len7 = 0;
			
			for(ShoppingVO vo : list) {
				to_len1 = String.valueOf(vo.getCustNo()).length(); 
				if(len1 < to_len1)
					len1 = to_len1;
				
				to_len2 = vo.getCustName().length();
				if(len2 < to_len2)
					len2 = to_len2;
				
				to_len3 = vo.getPhone().length();
				if(len3 < to_len3)
					len3 = to_len3;
				
				to_len4 = String.valueOf(vo.getGender()).length();
				if(len4 < to_len4)
					len4 = to_len4;
				
				to_len5 = vo.getJoinDate().length();
				if(len5 < to_len5)
					len5 = to_len5;
				
				to_len6 = vo.getGrade().length();
				if(len6 < to_len6)
					len6 = to_len6;
				
//				to_len7 = vo.getStatus().length();
//				if(len7 < to_len7)
//					len7 = to_len7;
				
			}
			
			len1 += 3;
			len2 += 3;
			len3 += 3;
			len4 += 3;
			len5 += 3;
			len6 += 3;
//			len7 += 3;
			
			System.out.println("총 " + list.size() + "명의 회원목록을 표시합니다.");
			System.out.println();
			System.out.printf("%-"+len1+"s%-"+len2+"s%-"+len3+"s%-"+len4+"s%-"+len5+"s%-"+len6+"s%n",
					len1N, len2N, len3N, len4N, len5N, len6N);
			for(ShoppingVO vo: list) {
				System.out.printf("%-"+len1+"d", vo.getCustNo());
				System.out.printf("%-"+len2+"s", vo.getCustName());
				System.out.printf("%-"+len3+"s", vo.getPhone());
				System.out.printf("%-"+len4+"s", vo.getGender());
				System.out.printf("%-"+len5+"s", vo.getJoinDate());
				System.out.printf("%-"+len6+"s", vo.getGrade());
//				System.out.printf("%-"+len7+"s", (vo.getStatus().equals("1")) ? "대출중":"반납완료");
				System.out.println();
			}
			System.out.println();
			return true;
		}else {
			System.out.println();
			System.out.println("등록된 회원 정보 없음");
			System.out.println();
			return false;
		}
	}
	
}
