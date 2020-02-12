package oracle.exam3;

import java.util.List;
import java.util.Scanner;

import oracle.dao.SawonDAO;
import oracle.dto.EmpVO;

/*	키보드로 부서번호, 직급을 입력
 * 	해당 사원정보 출력
 * 
 */

public class Exam_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SawonDAO dao = new SawonDAO();
		Scanner scn = new Scanner(System.in);
		System.out.print("부서번호:");
		int dno = scn.nextInt();
		System.out.print("직급:");
		String job = scn.next();
		
		List<EmpVO> empVos = dao.selectEmp(dno, job);
		
		if(empVos.size() > 0) {
			for(EmpVO empVo : empVos){
				System.out.print(empVo.getDno() + "\t");
				System.out.print(empVo.getEname() + "\t");
				System.out.print(empVo.getJob() + "\t");
				System.out.print(empVo.getManager() + "\t");
				System.out.print(empVo.getHireDate() + "\t");
				System.out.print(empVo.getSalary() + "\t");
				System.out.print(empVo.getCommission() + "\t");
				System.out.println(empVo.getDno());
			}
		}else {
			System.out.println("등록된 사용자 없음");
		}
		

		
		
		
	}

}
