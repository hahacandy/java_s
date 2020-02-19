package library.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import library.vo.LibraryVO;

public class UiManager {
	
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
	
	//날짜 입력기
	protected String inputDate(Scanner scn, String value, String mode) {
		String date = null;
		while(true) {
			System.out.print(value);
			date = scn.nextLine();
			
			if(mode.equalsIgnoreCase("skip") && date.length() == 0) {
				break;
			}else if(date.matches("((19[0-9]{2}|20[0-9]{2})-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]))|"
					+ "((19[0-9]{2}|20[0-9]{2})(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1]))")) {
				break;
			}else {
				System.out.println("대출일자 형식에 맞지않습니다. 다시 입력해주세요.");
			}
		}
		
		date = date.replaceAll("-", "");
		
		return date;
	}
	
	//예약 리스트 출력 메소드
	protected boolean printMember(List<LibraryVO> list) {
		if(list != null) {
			String len1N = "bun";
			String len2N = "name";
			String len3N = "phone";
			String len4N = "code";
			String len5N = "outDate";
			String len6N = "inDate";
			String len7N = "status";
			
			int len1 = len1N.length();
			int len2 = len2N.length();
			int len3 = len3N.length();
			int len4 = len4N.length();
			int len5 = len5N.length();
			int len6 = len6N.length();
			int len7 = len7N.length();
			
			int to_len1 = 0;
			int to_len2 = 0;
			int to_len3 = 0;
			int to_len4 = 0;
			int to_len5 = 0;
			int to_len6 = 0;
			int to_len7 = 0;
			
			for(LibraryVO vo : list) {
				to_len1 = String.valueOf(vo.getBun()).length(); 
				if(len1 < to_len1)
					len1 = to_len1;
				
				to_len2 = vo.getName().length();
				if(len2 < to_len2)
					len2 = to_len2;
				
				to_len3 = vo.getPhone().length();
				if(len3 < to_len3)
					len3 = to_len3;
				
				to_len4 = String.valueOf(vo.getCode()).length();
				if(len4 < to_len4)
					len4 = to_len4;
				
				to_len5 = vo.getOutDate().length();
				if(len5 < to_len5)
					len5 = to_len5;
				
				if(vo.getInDate() == null)
					vo.setInDate("");
				to_len6 = vo.getInDate().length();
				if(len6 < to_len6)
					len6 = to_len6;
				
				to_len7 = vo.getStatus().length();
				if(len7 < to_len7)
					len7 = to_len7;
				
			}
			
			len1 += 3;
			len2 += 3;
			len3 += 3;
			len4 += 3;
			len5 += 3;
			len6 += 3;
			len7 += 3;
			
			System.out.println("총 " + list.size() + "명의 대출현황을 조회합니다.");
			System.out.println();
			System.out.printf("%-"+len1+"s%-"+len2+"s%-"+len3+"s%-"+len4+"s%-"+len5+"s%-"+len6+"s%-"+len7+"s%n",
					len1N, len2N, len3N, len4N, len5N, len6N, len7N);
			for(LibraryVO vo: list) {
				System.out.printf("%-"+len1+"d", vo.getBun());
				System.out.printf("%-"+len2+"s", vo.getName());
				System.out.printf("%-"+len3+"s", vo.getPhone());
				System.out.printf("%-"+len4+"s", vo.getCode());
				System.out.printf("%-"+len5+"s", vo.getOutDate());
				System.out.printf("%-"+len6+"s", vo.getInDate());
				System.out.printf("%-"+len7+"s", (vo.getStatus().equals("1")) ? "대출중":"반납완료");
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
