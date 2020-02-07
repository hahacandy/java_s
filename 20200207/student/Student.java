package textstream;

import java.util.StringTokenizer;

public class Student {
	int bun;
	String name;
	String sung;
	int kor;
	int eng;
	int mat;
	
	int tot;
	double ave;
	
	public Student(String studentData) {
		inputData(studentData);
	}
	
	void inputData(String studentData) {
		StringTokenizer st = new StringTokenizer(studentData, ",");
		bun = Integer.valueOf(st.nextToken());
		name = st.nextToken();
		sung = st.nextToken();
		kor = Integer.valueOf(st.nextToken());
		eng = Integer.valueOf(st.nextToken());
		mat = Integer.valueOf(st.nextToken());
		
		tot = kor+eng+mat;
		ave = Math.round((tot/3.*100))/100.;
	}
}
