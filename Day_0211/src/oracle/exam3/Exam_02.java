package oracle.exam3;

import java.util.List;
import java.util.Scanner;

import model.EmpVO;
import oracle.dao.SawonDAO;

/*	키보드 입력
 * 	부서번호 또는 직급(업무명)이 같은 사원 정보 출력
 * 
 */

public class Exam_02 {

	public static void main(String[] args) {
		SawonDAO dao = new SawonDAO();
		Scanner scn = new Scanner(System.in);
		System.out.print("부서번호:");
		int dno = scn.nextInt();
		System.out.print("직급:");
		String job = scn.next().toUpperCase();
		
		List<EmpVO> empVos = dao.selectOREmp(dno, job);
		
		if(empVos.size() > 0) {
			for(EmpVO empVo : empVos){
				System.out.print(empVo.getEno() + "\t");
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
