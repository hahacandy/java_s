package studentexam.main;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//생년월일
		String birth = null;
		String birthC[] = null;
		Scanner scn =new Scanner(System.in); 
		

		System.out.print("생년월일(xxxx-xx-xx):");
		birth = scn.next();
		
		if(birth.matches("(19[0-9]{2}|20[0-9]{2})-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])")) {
			System.out.println("형식에  맞음");
		}else {
			System.out.println("형식이상함");
		}
		


	}

}
