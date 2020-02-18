package studentexam.main;

import java.util.Scanner;

import studentexam.ui.Ui;

public class StudentExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		boolean escape = false;
		Ui ui = new Ui();
		int sel = 0;
		
		while(!escape) {
			System.out.println("학생정보 관리 시스템");
			System.out.println("1: 학생정보 관리");
			System.out.println("2: 성적정보 관리");
			System.out.println("3: 장학금 관리");
			System.out.println("4: 프로그램 정보");
			
			System.out.print("선택:");
			sel = scn.nextInt();
			
			if(sel == 1) {
				ui.studentInfoMana(scn);
			}else if(sel == 2) {
				System.out.println("아직 지원되지 않는 기능");
			}else if(sel == 3) {
				System.out.println("아직 지원되지 않는 기능");
			}else if(sel == 4) {
				if(ui.isYes(scn, "정말로 종료 할까요?")) {
					System.out.println("프로그램 종료");
					break;
				}
			}
			
		}
	}

}
