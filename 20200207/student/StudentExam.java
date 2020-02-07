package textstream;

import java.util.ArrayList;

/*	���Ϸ� ���� �Է�	(data.txt)
 * 	��� ���� (���� ���:dataout.txt, �ܼ� ���)
 * 	��ȣ	�̸�		����	����	����	����	����	���
 * 	1	ȫ�浿	����	23	43	23 	xx	xx
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
