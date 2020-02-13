package view;

import java.util.List;
import java.util.Scanner;

import model.DeptDao;
import model.DeptVO;

public class UI {
	//목록 리스트 
	public void getDeptList() {
		List<DeptVO> deptVOs = new DeptDao().selectAllDept();
		
		if(deptVOs != null) {
			System.out.println();
			System.out.println("DNO\tDNAME\t\tLOC");
			for(DeptVO deptVO : deptVOs) {
				System.out.print(deptVO.getDno() + "\t");
				String dname = deptVO.getDname();
				if(dname.length() <= 7)
					dname = dname + "\t";
				System.out.print(dname + "\t");
				System.out.println(deptVO.getLoc());
			}
			System.out.println();
		}else {
			System.out.println();
			System.out.println("등록된 정보 없음.");
			System.out.println();
		}
		
		
		//db처리 select 메소드 출력
	}
	
	//등록 리스트
	public void insertDept(Scanner scn) {
		System.out.println("부서정보 등록");
		System.out.print("부서번호:");
		int dno=scn.nextInt();
		System.out.print("부서명:");
		String dname=scn.next();
		System.out.print("지역명:");
		String loc = scn.next();
		
		//db처리 등록 메소드 실행
		int row = new DeptDao().insertDept(dno, dname, loc);
		if(row > 0) {
			System.out.println();
			System.out.println("부서 정보 등록 완료");
			System.out.println();
		}else {
			System.out.println();
			System.out.println("부서 정보 등록 실패");
			System.out.println();
		}
	}
	
	//삭제
	public void removeDept(Scanner scn) {
		System.out.print("삭제하려는 department의 부서번호:");
		int dno = scn.nextInt();
		
		//db처리 메소드 실행
		int row = new DeptDao().deleteDept(dno);
		if(row > 0) {
			System.out.println();
			System.out.println("부서 정보 삭제 완료");
			System.out.println();
		}else {
			System.out.println();
			System.out.println("부서 정보 삭제 실패");
			System.out.println();
		}
	}
	
	
	//수정
	public void modifyDept(Scanner scn) {
		System.out.print("변경하려는 department의 부서번호:");
		int dno = scn.nextInt();
		
		System.out.print("변경되는 부서 이름:");
		String dname = scn.next();
		System.out.print("변경되는 지역 이름:");
		String loc = scn.next();
		
		//db처리 메소드 실행
		int row = new DeptDao().updateDept(dno, dname, loc);
		if(row > 0) {
			System.out.println();
			System.out.println("부서 정보 수정 완료");
			System.out.println();
		}else {
			System.out.println();
			System.out.println("부서 정보 수정 실패");
			System.out.println();
		}
		
	}
	
	//검색 리스트
	public void searchDept(Scanner scn) {
		System.out.print("부서번호:");
		int dno = scn.nextInt();
		
		List<DeptVO> deptVOs = new DeptDao().selectWhereDept(dno);
		
		if(deptVOs != null) {
			System.out.println();
			System.out.println("DNO\tDNAME\t\tLOC");
			for(DeptVO deptVO : deptVOs) {
				System.out.print(deptVO.getDno() + "\t");
				String dname = deptVO.getDname();
				if(dname.length() <= 7)
					dname = dname + "\t";
				System.out.print(dname + "\t");
				System.out.println(deptVO.getLoc());
			}
			System.out.println();
		}else {
			System.out.println();
			System.out.println("등록된 정보 없음.");
			System.out.println();
		}
	}
	
	//삭제 리스트
}
