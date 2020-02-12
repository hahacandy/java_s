package exam;

import java.util.List;

import model.DataDAO;
import model.EmpVO;

public class Exam_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataDAO dao = new DataDAO();
		List<EmpVO> list = dao.exam_01();
		
		if(!list.isEmpty()) {
			//출력
			System.out.println("이름\t월급\t월급+300");
			for(EmpVO empVO : list) {
				System.out.print(empVO.getEname() + "\t");
				System.out.print(empVO.getSalary() + "\t");
				System.out.println(empVO.getSalaryPlus());
			}
		}else {
			System.out.println("등록된 자료가 없음");
		}
	}

}
