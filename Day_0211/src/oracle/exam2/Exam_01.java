package oracle.exam2;

import java.util.List;

import model.DeptVO;
import model.EmpVO;
import oracle.dao.SawonDAO;

public class Exam_01 {

	public static void main(String[] args) {
		SawonDAO sawonDAO = new SawonDAO();
		List<DeptVO> deptList = sawonDAO.deptList();
		
		if(deptList.size() == 0) {
			System.out.println("등록된 자료가 없음");
		}else {
			for(DeptVO dept : deptList) {
				System.out.print(dept.getDno() + "\t");
				System.out.print(dept.getDname() + "\t");
				System.out.println(dept.getLoc());
			}
		}
		
		List<EmpVO> empList = sawonDAO.empList();
		if(deptList.size() == 0) {
			System.out.println("등록된 자료가 없음");
		}else {
			for(EmpVO emp : empList) {
				System.out.print(emp.getEno() + "\t");
				System.out.print(emp.getEname() + "\t");
				System.out.print(emp.getJob() + "\t");
				System.out.print(emp.getManager() + "\t");
				System.out.print(emp.getHireDate() + "\t");
				System.out.print(emp.getSalary() + "\t");
				System.out.print(emp.getCommission() + "\t");
				System.out.println(emp.getDno());
			}
		}
		
		
		
		
	}

}
