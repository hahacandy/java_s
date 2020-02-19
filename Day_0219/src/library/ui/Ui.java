package library.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import library.dao.LibraryDao;
import library.vo.LibraryVO;

public class Ui extends UiManager{
	LibraryDao dao = new LibraryDao();
	
	public void start(Scanner scn) {
		boolean escape = false;
		int sel = 0;
		while(!escape) {
			System.out.println("도서대출 관리 프로그램");
			System.out.println("[1] 도서 대출 예약 등록");
			System.out.println("[2] 도서 대출 현황 조회");
			System.out.println("[3] 도서 대출 예약 수정");
			System.out.println("[4] 도서 대출 반납 등록");
			System.out.println("[5] 프로그램 종료");
			
			System.out.print("메뉴를 선택:");
			sel = scn.nextInt();
			
			if(sel==1) {
				reservationBook(scn);
			}else if(sel==2) {
				statusBook();
			}else if(sel==3) {
				reservationModi(scn);
			}else if(sel==4) {
				returnBook(scn);
			}else if(sel==5) {
				if(isYes(scn, "정말로 종료할까요?")) {
					System.out.println("프로그램 종료됨.");
				}
			}
		}
	}
	
	//[4] 도서 대출 반납 등록
	private void returnBook(Scanner scn) {
		int row = 0;
		int bun = 0;
		List<LibraryVO> list = null;
		
		System.out.print("대출번호:");
		bun = scn.nextInt();
		scn.nextLine();
		list = dao.getReservation(bun);
		
		if(printMember(list)) {
			if(isYes(scn, "도서반납을 처리합니까?")) {
				row = dao.returnReservation(bun);
				printUpdate(row, "도서반납을 완료하였습니다.", "도서반납을 실패하였습니다.");
			}
		}
	}
	
	//[3] 도서 대출 예약 수정
	private void reservationModi(Scanner scn) {
		int row = 0;
		List<LibraryVO> list = null;
		List<Object> IInfos = new ArrayList<Object>(); //db로 전송하기전의 가공되기 전의 리스트
		List<String> infos = null;						//db로 전송하기 위해 가공된 리스트
	
		int bun = 0;
		String name = null;
		String phone = null;
		int code = 0;
		String outDate = null;
		String inDate = null;
		Boolean isInDate = false; //indate 입력했을 경우 쿼리를 다르게 사용하기 위함 
		
		System.out.println("[3] 도서 대출 예약 수정");
		
		
		System.out.print("대출번호:");
		bun = scn.nextInt();
		scn.nextLine();
		list = dao.getReservation(bun);
		
		if(printMember(list)) {
			
			System.out.print("바꾸려는 고객성명:");
			name = scn.nextLine();
			
			System.out.print("바꾸려는 연락처:");
			phone = scn.nextLine();
			
			while(true) {
				System.out.print("바꾸려는 도서코드:");
				code = scn.nextInt();

				try {
					code = Integer.valueOf(scn.nextLine());
				}catch (Exception e) {}
				
				if(code >= 100 && code <= 999 || code == 0) {
					break;
				}
					
				else
					System.out.println("도서코드는 숫자 3자리 입니다.");
			}
			

			outDate = inputDate(scn, "바꾸려는 대출일자:");
			
			inDate = inputDate(scn, "바꾸려는 반납일자:");
			
			if(isYes(scn, "수정하시겠습니까?")) {
				if(name.length() > 0 )
					IInfos.add(name);
				else
					IInfos.add(list.get(0).getName());
				
				if(phone.length() > 0 )
					IInfos.add(phone);
				else
					IInfos.add(list.get(0).getPhone());
				
				if(code > 0 )
					IInfos.add(code);
				else
					IInfos.add(list.get(0).getCode());
				
				if(outDate.length() > 0 )
					IInfos.add(outDate);
				else
					IInfos.add(list.get(0).getOutDate());
				
				if(inDate.length() > 0 ) {
					isInDate = true;
					IInfos.add(inDate);
				}	
				
				infos = inputListInfos(IInfos);
				
				row = dao.modiReservation(bun, infos, isInDate);
				
				printUpdate(row, "도서 대출 예약 수정을 완료하였습니다.", "도서 대출 예약 수정을 실패하였습니다.");
			}
			
		}
		
		
		
	}
		

	//[2] 도서 대출 현황 조회
	private void statusBook() {
		System.out.println("[2] 도서 대출 현황 조회");
		System.out.println();
		List<LibraryVO> list = dao.getAllReservation();
		
		printMember(list);
	}
	
	
	//[1] 도서 대출 예약 등록
	private void reservationBook(Scanner scn) {
		int row = 0;
		List<Object> IInfos = new ArrayList<Object>(); //db로 전송하기전의 가공되기 전의 리스트
		List<String> infos = null;						//db로 전송하기 위해 가공된 리스트
		
		String name = null;
		String phone = null;
		int code = 0;
		String outDate = null;
		String inDate = null;
		Boolean isInDate = false; //indate 입력했을 경우 쿼리를 다르게 사용하기 위함 
		
		System.out.println("[1] 도서 대출 예약 등록");
		
		System.out.print("고객성명:");
		name = scn.next();
		
		System.out.print("연락처:");
		phone = scn.next();
		
		while(true) {
			System.out.print("도서코드:");
			code = scn.nextInt();
			scn.nextLine();
			if(code >= 100 && code <= 999)
				break;
			else
				System.out.println("도서코드는 숫자 3자리 입니다.");
		}
		

		outDate = inputDate(scn, "대출일자:");
		
		
		inDate = inputDate(scn, "반납일자:");
		
		if(isYes(scn, "등록하시겠습니까?")) {
			IInfos.add(name);
			IInfos.add(phone);
			IInfos.add(code);
			IInfos.add(outDate);
			
			if(inDate.length() > 0 ) {
				isInDate = true;
				IInfos.add(inDate);
			}	
			
			infos = inputListInfos(IInfos);
			
			row = dao.regReservation(infos, isInDate);
			
			printUpdate(row, "도서 대출 예약 등록을 완료하였습니다.", "도서 대출 예약 등록을 실패하였습니다.");
		}
	}
	

}
