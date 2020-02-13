package customer.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import customer.model.CusDao;
import customer.model.CusVO;

public class UI {
	
	public void regCus(Scanner scn) {
		System.out.print("추가하려는 고객 번호 입력:");
		int num = scn.nextInt();
		
		List<String> info = new ArrayList<String>();
		
		System.out.print("추가하려는 이름 입력:");
		info.add(scn.next());
		
		System.out.print("추가하려는 전화번호 입력:");
		info.add(scn.next());
		
		System.out.print("추가하려는 주소 입력:");
		info.add(scn.next());
		
		System.out.print("추가하려는 회사주소 입력:");
		info.add(scn.next());
		
		System.out.print("추가하려는 생일(예:2010-01-02) 입력:");
		info.add(scn.next());
		
		System.out.print("추가하려는 성별 입력:");
		info.add(scn.next());
		
		CusDao cusDao = new CusDao();
		int row  = cusDao.insertQuery(num, info);
		
		printUpdate("추가", row, num);
	}
	
	public void modifyCus(Scanner scn) {
		System.out.print("변경하려는 고객 번호 입력:");
		int num = scn.nextInt();
		
		List<String> info = new ArrayList<String>();
		
		System.out.print("변경되는 이름 입력:");
		info.add(scn.next());
		
		System.out.print("변경되는 전화번호 입력:");
		info.add(scn.next());
		
		System.out.print("변경되는 주소 입력:");
		info.add(scn.next());
		
		System.out.print("변경되는 회사주소 입력:");
		info.add(scn.next());
		
		System.out.print("변경되는 생일(예:2010-01-02) 입력:");
		info.add(scn.next());
		
		System.out.print("변경되는 성별 입력:");
		info.add(scn.next());

		CusDao cusDao = new CusDao();
		int row  = cusDao.updateQuery(num, info);
		
		printUpdate("변경", row, num);
	}
	
	public void removeCus(Scanner scn) {
		CusDao cusDao = new CusDao();
		
		System.out.print("삭제하려는 고객 번호 입력:");
		int num = scn.nextInt();
		
		List<String> wheres = new ArrayList<String>();
		wheres.add("int,num," + String.valueOf(num));

		int row  = (int)cusDao.query("DELETE", "CUSTOM", wheres);
		
		printUpdate("삭제", row, num);
	}
	
	public void printUpdate(String update, int row, int num) {
		System.out.println();
		if(row > 0) {
			System.out.println("고객번호:" + num + " " + update + " 완료");
		}else {
			System.out.println("고객번호:" + num + " " + update + " 실패");
		}
		System.out.println();
	}
	
	
	public void printCusList(List<CusVO> list) {
		
		if(list != null) {
			System.out.println();
			System.out.println("번호\t이름\t전화\t주소\t회사주소\t생일\t\t\t성별\t생성일자");
			
			for(CusVO cus : list) {
				System.out.print(cus.getNum() + "\t");
				System.out.print(cus.getName() + "\t");
				System.out.print(cus.getTel() + "\t");
				System.out.print(cus.getAddr() + "\t");
				System.out.print(cus.getOffice() + "\t");
				System.out.print(cus.getBirthday() + "\t");
				System.out.print(cus.getSex() + "\t");
				System.out.println(cus.getInDate());
			}
			System.out.println();
		}else {
			System.out.println();
			System.out.println("등록된 사용자 없음.");
			System.out.println();
		}
	}
	
	public void viewCusList() {
		CusDao cusDao = new CusDao();
		
		List<CusVO> list = (List<CusVO>)cusDao.query("SELECT *", "CUSTOM", null);
		
		printCusList(list);
		
	}
	
	public void searchCus(Scanner scn) {
		CusDao cusDao = new CusDao();
		
		System.out.print("검색하는 고객 번호 입력:");
		int num = scn.nextInt();
		
		List<String> wheres = new ArrayList<String>();
		wheres.add("int,num," + String.valueOf(num));

		List<CusVO> list = (List<CusVO>)cusDao.query("SELECT *", "CUSTOM", wheres);
		
		printCusList(list);
		
	}
}
