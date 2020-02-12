package exam;

import java.util.List;

import model.DataDAO;
import model.EmpVO;

public class Exam_02 {

	public static void main(String[] args) {
		
		DataDAO dao = new DataDAO();
		List<EmpVO> empVOs = dao.exam_02();
		
		if(empVOs != null) {
			for(EmpVO empVO : empVOs) {
				System.out.print(empVO.getEname() + "\t");
				System.out.print(empVO.getSalary() + "\t");
				System.out.println(empVO.getSalaryPlus());
			}
		}else {
			System.out.println("등록된 사람 없음");
		}
		
		

	}

}
