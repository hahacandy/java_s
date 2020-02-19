package library.main;

import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scn =new Scanner(System.in);
		String outDate = null;
		
		String date = null;
		while(true) {
			date = scn.next();
			
			if(date.matches("((19[0-9]{2}|20[0-9]{2})-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]))|"
					+ "((19[0-9]{2}|20[0-9]{2})(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1]))")) {
				break;
			}else {
				System.out.println("대출일자 형식에 맞지않습니다. 다시 입력해주세요.");
			}
		}
		
		date = date.replaceAll("-", "");
		
		System.out.println(date);
		
		
//		String asdf = "asdf";
//		Object asdf2 = 0;
//		
//		
//		if(asdf instanceof String) {
//			System.out.println("스트링문자열");
//		}
//		if(asdf2 instanceof Integer){
//			System.out.println("인트");
//		}
	}

}
