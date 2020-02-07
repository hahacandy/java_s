package textstream;

import java.util.ArrayList;

/*	파일로 부터 입력	(data.txt)
 * 	출력 예시 (파일 출력:dataout.txt, 콘솔 출력)
 * 	번호	이름		성별	국어	영어	수학	총점	평균
 * 	1	홍길동	남자	23	43	23 	xx	xx
 */

public class StudentExam {

	public static void main(String[] args) {
		
		ArrayList<String> studentsTxtData = StudentData.getTxtData("C:\\data\\data.txt");
		StudentData studentData = new StudentData();
		
		for(String studentTxtData : studentsTxtData) {
			studentData.inputStudent(new Student(studentTxtData));
		}
		
		studentData.printConsole();
		studentData.printFile();
		
	}

}
