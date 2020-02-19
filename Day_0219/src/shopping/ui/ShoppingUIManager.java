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
			List<String> lenNs = new ArrayList<String>();
			List<Integer> lens = new ArrayList<Integer>();
			
			lenNs.add("custno");
			lenNs.add("custname");
			lenNs.add("phone");
			lenNs.add("gender");
			lenNs.add("joindate");
			lenNs.add("grade");
			
			for(int i=0; i<lenNs.size(); i++) {
				lens.add(lenNs.get(i).length());
			}
			
			int to_len = 0;
			for(int i=0; i<list.size(); i++) {
				
				to_len = getLength(list.get(i).getCustNo());
				if(lens.get(0) < to_len) {
					lens.set(0, to_len);
				}
				
				to_len = getLength(list.get(i).getCustName());
				if(lens.get(1) < to_len) {
					lens.set(1, to_len);
				}
				
				to_len = getLength(list.get(i).getPhone());
				if(lens.get(2) < to_len) {
					lens.set(2, to_len);
				}

				to_len = getLength(list.get(i).getGender());
				if(lens.get(3) < to_len) {
					lens.set(3, to_len);
				}
				
				to_len = getLength(list.get(i).getJoinDate());
				if(lens.get(4) < to_len) {
					lens.set(4, to_len);
				}
				
				to_len = getLength(list.get(i).getGrade());
				if(lens.get(5) < to_len) {
					lens.set(5, to_len);
				}
				
			}
			
			for(int i=0; i<lens.size(); i++) {
				lens.set(i, lens.get(i)+3);
			}
			
			System.out.println();
			System.out.println("총 " + list.size() + "명의 회원목록을 표시합니다.");
			System.out.println();
			
			for(int i=0; i<lenNs.size(); i++) {
				System.out.printf("%-"+lens.get(i)+"s", lenNs.get(i));
			}
			System.out.println();
			
			for(ShoppingVO vo: list) {
				System.out.printf("%-"+lens.get(0)+"d", vo.getCustNo());
				System.out.printf("%-"+lens.get(1)+"s", vo.getCustName());
				System.out.printf("%-"+lens.get(2)+"s", vo.getPhone());
				System.out.printf("%-"+lens.get(3)+"s", vo.getGender());
				System.out.printf("%-"+lens.get(4)+"s", vo.getJoinDate());
				System.out.printf("%-"+lens.get(5)+"s", vo.getGrade());
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
	
	private int getLength(Object input) {
		int length=0;
		
		if(input instanceof String)
			length = ((String) input).length();
		else if (input instanceof Integer)
			length = String.valueOf(((Integer)input)).length();
		
		return length;
	}
	
}
