package textstream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class StudentData {
	
	ArrayList<Student> students;

	public StudentData() {
		students = new ArrayList<Student>();
	}
	
	void inputStudent(Student student) {
		students.add(student);
	}
	
	void printFile() {
		File file = new File("c:\\data\\data2.txt");
		PrintWriter pwf;
		try {
			pwf = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			pwf.println("번호\t이름\t성별\t국어\t영어\t수학\t총점\t평균");
			for(Student student : students) {
				pwf.print(student.bun + "\t");
				pwf.print(student.name + "\t");
				String sung = student.sung;
				if(sung.equalsIgnoreCase("m"))
					sung = "남";
				else if (sung.equalsIgnoreCase("f"))
					sung = "여";
				else
					sung = "?";
				pwf.print(sung + "\t");
				pwf.print(student.kor + "\t");
				pwf.print(student.eng + "\t");
				pwf.print(student.mat + "\t");
				pwf.print(student.tot + "\t");
				pwf.println(student.ave);

			}
			pwf.println();
			pwf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	void printConsole() {
		System.out.println("번호\t이름\t성별\t국어\t영어\t수학\t총점\t평균");
		for(Student student : students) {
			System.out.print(student.bun + "\t");
			System.out.print(student.name + "\t");
			String sung = student.sung;
			if(sung.equalsIgnoreCase("m"))
				sung = "남";
			else if (sung.equalsIgnoreCase("f"))
				sung = "여";
			else
				sung = "?";
			System.out.print(sung + "\t");
			System.out.print(student.kor + "\t");
			System.out.print(student.eng + "\t");
			System.out.print(student.mat + "\t");
			System.out.print(student.tot + "\t");
			System.out.print(student.ave + "\t");
			
			System.out.println();
		}
	}
	
	
	
	static ArrayList<String> getTxtData(String path) {
		ArrayList<String> studentsData = new ArrayList<String>();
		
		BufferedReader br;
		
		try {
			br = new BufferedReader(new FileReader(new File(path)));
			
			while(true) {
				String student;
				try {
					student = br.readLine();
					if(student != null) {
						studentsData.add(student);
					}else {
						break;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (FileNotFoundException e) {}
		
		return studentsData;
	}
	
}
