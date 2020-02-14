package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.DeptDao;
import model.DeptVO;
import model.EmpDao;
import model.EmpVO;

public class UI {
	
	
	//사원 삭제
	public void removeSawon(Scanner scn) {
		System.out.print("삭제하려는 사원번호를 입력:");
		int eno = scn.nextInt();
		
		List<EmpVO> empVO = new EmpDao().selectOneEmp(eno);
		
		if(empVO != null) {
			printSawonList(empVO);
			System.out.print("해당 사원을 정말 삭제할껀가요 y/n:");
			String sel = scn.next();
			if(sel.equalsIgnoreCase("y")){
				//해번 사원번호의 사원 삭제
				int row = new EmpDao().deleteQuery(eno);
				printUpdate("사원 삭제", row);
			}
		}else {
			System.out.println("해당 사원번호가 존재하지 않음.");
		}
		

	}
	
	//사원 정보 수정
	public void modifySawon(Scanner scn) {
		List<String> info = new ArrayList<String>();
		
		System.out.print("수정하려는 사원의 사원번호를 입력:");
		info.add(scn.next());
		
		System.out.print("변경되는 이름을 입력:");
		info.add("str,"+scn.next());
		
		System.out.print("변경되는 직업을 입력:");
		info.add("str,"+scn.next());
		
		System.out.print("변경되는 상사번호를 입력:");
		info.add("int,"+scn.next());
		
		System.out.print("변경되는 입사일자(xxxx-xx-xx)를 입력:");
		info.add("str,"+scn.next());
		
		System.out.print("변경되는 월급을 입력:");
		info.add("int,"+scn.next());
		
		System.out.print("변경되는 commission를 입력:");
		info.add("int,"+scn.next());
		
		System.out.print("변경되는 부서번호를 입력:");
		info.add("int,"+scn.next());
		
		//부서 수정
		int row = new EmpDao().updateQuery(info);
		printUpdate("사원 수정", row);
	}
	
	//사원 등록 메소드
	public void newRegi(Scanner scn) {
		
		List<String> newInfo = new ArrayList<String>();
		System.out.print("사원이름:");
		newInfo.add(scn.next());
		System.out.print("업 무 명:");
		newInfo.add(scn.next());
		System.out.print("상사번호:");
		newInfo.add(scn.next());
		System.out.print("입사일자(xxxx-xx-xx):");
		newInfo.add(scn.next());
		System.out.print("급여:");
		newInfo.add(scn.next());
		System.out.print("커 미 션:");
		newInfo.add(scn.next());
		
		//부서가 어떤게 존재하는지 불러온 뒤 출력해줘야함
		List<DeptVO> deptVOs = new DeptDao().selectAllDept();
		boolean escape = false;
		int dno = 0;
		while(!escape) {
			System.out.print("부서정보(");
			for(int i=0; i<deptVOs.size(); i++) {
				System.out.print(deptVOs.get(i).getDno() + ":" + deptVOs.get(i).getDname());
				if(i != deptVOs.size() -1)
					System.out.print(", ");
				else
					System.out.println(")");
			}	
			
			System.out.print("부서번호:");
			dno = scn.nextInt();
			
			for(DeptVO vo : deptVOs) {
				if(vo.getDno() == dno) {
					escape = true;
				}
			}
			
			if(escape)
				continue;
			
			System.out.println("\n존재하지 않는 부서번호!\n");
			
		}
		newInfo.add(String.valueOf(dno));
		
		
		//신규 가입 쿼리 떄림
		int row = new EmpDao().insertQuery(newInfo);
		//결과 출력
		printUpdate("신규가입", row);
		
	}
	
	//생성, 수정, 삭제 의 결과를 출력
	private void printUpdate(String update, int row) {
		System.out.println();
		if(row > 0) {
			System.out.println(update + " 완료");
		}else {
			System.out.println(update + " 실패");
		}
		System.out.println();
	}
	
	
	
	//사원 검색 메소드
	public void searchSawon(Scanner scn) {
		System.out.print("검색할 사원이름을 입력:");
		String ename = scn.next();
		
		List<EmpVO> empVOs = new EmpDao().selectSearchEmp(ename);
		
		printSawonList(empVOs);
	}
	
	//사원 전체 출력
	//모든 사원 리스트를 가져와, 출력 메소드로 넘김
	public void getAllSawonList(){
		List<EmpVO> list = new EmpDao().selectAllEmp();
		
		printSawonList(list);
	}
	//사원 리스트 출력 메소드
	private void printSawonList(List<EmpVO> list) {
		if(list != null) {
			System.out.println();
			System.out.printf("%-10s%-10s%-10s%-10s%-24s%-15s%-15s%-10s%n", "eno", "ename", "job", "manager", "hiredate", "salary", "Commission", "dname" );
			for(EmpVO emp : list) {
				System.out.printf("%-10s", emp.getEno());
				System.out.printf("%-10s", emp.getEname());
				System.out.printf("%-10s", emp.getJob());
				
				if(emp.getManager2() != null)
					System.out.printf("%-10s", emp.getManager2());
				else
					System.out.printf("%-10s", emp.getManager());
				
				System.out.printf("%-24s", emp.getHireDate());
				System.out.printf("%-15s", emp.getSalary());
				System.out.printf("%-15s", emp.getCommission());
				System.out.printf("%-10s%n", emp.getDname());

			}
			System.out.println();
		}else {
			System.out.println();
			System.out.println("등록된 사원 정보 없음");
			System.out.println();
		}
	}
	
}
