package exam;

import java.util.List;

import model.DataDAO;
import model.EmpVO;

public class Exam_13_2 {

	public static void main(String[] args) {
		DataDAO dao = new DataDAO();
		List<EmpVO> empVOs = dao.exam_13_2();
		
		if(empVOs != null) {
			for(EmpVO empVO : empVOs) {
				System.out.print(empVO.getEname() + "\t");
				System.out.println();
			}
		}else {
			System.out.println("등록된 사람 없음");
		}
	}

}
