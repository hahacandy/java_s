package model;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;

public class DataDAO {
	DBManager manager = DBManager.getInstance();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<EmpVO> exam_02(){
		List<EmpVO> list = null;
		String query = "SELECT eno, ename, salary, job, manager, hiredate, salary, commission, dno, salary*12+100 as salaryPlus FROM employee "
				+ "ORDER BY salaryPlus desc";

		list = getResult(query, true);

		return list;
	}
	
	//귀찮아서 * 함
	public List<EmpVO> exam_03(){
		List<EmpVO> list = null;
		String query = "SELECT * FROM employee WHERE salary>2000 ORDER BY salary DESC";

		list = getResult(query, false);

		return list;
	}
	
	public List<EmpVO> exam_04(){
		List<EmpVO> list = null;
		String query = "SELECT * FROM employee WHERE eno=7788";

		list = getResult(query, false);

		return list;
	}
	
	public List<EmpVO> exam_05(){
		List<EmpVO> list = null;
		String query = "SELECT * FROM employee "
				+ "WHERE salary NOT BETWEEN 2000 AND 3000";

		list = getResult(query, false);

		return list;
	}
	
	public List<EmpVO> exam_06(){
		List<EmpVO> list = null;
		String query = "SELECT * FROM employee "
				+ "WHERE hiredate BETWEEN '81/02/20' AND '81/05/01'";

		list = getResult(query, false);

		return list;
	}
	
	public List<EmpVO> exam_07(){
		List<EmpVO> list = null;
		String query = "SELECT * FROM employee "
				+ "WHERE dno IN(20,30) ORDER BY ename";

		list = getResult(query, false);

		return list;
	}
	
	public List<EmpVO> exam_08(){
		List<EmpVO> list = null;
		String query = "SELECT * FROM employee "
				+ "WHERE salary BETWEEN 2000 AND 3000 "
				+ "AND dno IN(20,30) ORDER BY ename asc";

		list = getResult(query, false);

		return list;
	}
	
	public List<EmpVO> exam_09(){
		List<EmpVO> list = null;
		String query = "SELECT * FROM employee "
				+ "WHERE hiredate LIKE '81%'";

		list = getResult(query, false);

		return list;
	}
	
	public List<EmpVO> exam_10(){
		List<EmpVO> list = null;
		String query = "SELECT * FROM employee "
				+ "WHERE manager IS null";

		list = getResult(query, false);

		return list;
	}
	
	public List<EmpVO> exam_10_2(){
		List<EmpVO> list = null;
		List<String> columns = new ArrayList<String>();
		
		columns.add("ename");
		columns.add("job");
		String from = "employee";
		String condition = "WHERE manager IS null";
		
		list = getResult2(columns , from, condition);

		return list;
	}
	
	public List<EmpVO> exam_11(){
		List<EmpVO> list = null;
		String query = "SELECT * FROM employee "
				+ "WHERE commission IS NOT null "
				+ "ORDER BY salary, commission";

		list = getResult(query, false);

		return list;
	}
	
	public List<EmpVO> exam_11_2(){
		List<EmpVO> list = null;
		List<String> columns = new ArrayList<String>();
		
		columns.add("ename");
		columns.add("salary");
		columns.add("commission");
		String from = "employee";
		String condition = "WHERE commission IS NOT null "
				+ "ORDER BY salary, commission";
		
		list = getResult2(columns , from, condition);

		return list;
	}
	
	public List<EmpVO> exam_12(){
		List<EmpVO> list = null;
		String query = "SELECT * FROM employee "
				+ "WHERE ename LIKE '__R%'";

		list = getResult(query, false);

		return list;
	}
	
	public List<EmpVO> exam_12_2(){
		List<EmpVO> list = null;
		List<String> columns = new ArrayList<String>();
		
		columns.add("ename");
		String from = "employee";
		String condition = "WHERE ename LIKE '__R%'";
		
		list = getResult2(columns , from, condition);

		return list;
	}
	
	public List<EmpVO> exam_13(){
		List<EmpVO> list = null;
		String query = "SELECT * FROM employee "
				+ "WHERE ename LIKE '%A%' AND ename LIKE '%E%'";

		list = getResult(query, false);

		return list;
	}
	
	public List<EmpVO> exam_13_2(){
		List<EmpVO> list = null;
		List<String> columns = new ArrayList<String>();
		
		columns.add("ename");
		String from = "employee";
		String condition = "WHERE ename LIKE '%A%' AND ename LIKE '%E%'";

		list = getResult2(columns , from, condition);

		return list;
	
	}
	
	public List<EmpVO> exam_14(){
		List<EmpVO> list = null;
		String query = "SELECT * FROM employee "
				+ "WHERE job IN('CLERK', 'SALESMAN') "
				+ "AND salary NOT IN(1600, 950, 1300)";

		list = getResult(query, false);

		return list;
	}
	
	public List<EmpVO> exam_14_2(){
		List<EmpVO> list = null;
		List<String> columns = new ArrayList<String>();
		
		columns.add("ename");
		columns.add("job");
		columns.add("salary");
		String from = "employee";
		String condition = "WHERE job IN('CLERK', 'SALESMAN') AND salary NOT IN(1600, 950, 1300)";

		list = getResult2(columns , from, condition);

		return list;
	}
	
	public List<EmpVO> exam_15(){
		List<EmpVO> list = null;
		String query = "SELECT * FROM employee "
				+ "WHERE commission>=500";

		list = getResult(query, false);

		return list;
	}
	
	public List<EmpVO> exam_15_2(){
		List<EmpVO> list = null;
		List<String> columns = new ArrayList<String>();
		
		columns.add("ename");
		columns.add("salary");
		columns.add("commission");
		String from = "employee";
		String condition = "WHERE commission>=500";

		list = getResult2(columns , from, condition);

		return list;
	}
	
//	public List<EmpVO> exam_15_3(){
//		List<EmpVO> list = null;
//		List<String> columns = new ArrayList<String>();
//		
//		columns.add("str,ename");
//		columns.add("int,salary");
//		columns.add("int,commission");
//		String from = "employee";
//		String condition = "WHERE commission>=500";
//
//		list = getResult3(columns , from, condition);
//
//		return list;
//	}
	
//	public List<EmpVO> getResult3(List<String> columns_q, String from_q, String condition_q){
//		
//		String columns_Q = "SELECT ";
//		for(int i=0; i<columns_q.size(); i++) {
//			if(i < columns_q.size()-1)
//				columns_Q += columns_q.get(i).split(",")[1] + ", ";
//			else 
//				columns_Q += columns_q.get(i).split(",")[1] + " ";
//		}
//		from_q = "FROM " + from_q + " ";
//		String query = columns_Q + from_q + condition_q;
//		
//		List<EmpVO> list = new ArrayList<EmpVO>();
//		
//		try {
//			conn = manager.getConnection();
//			pstmt = conn.prepareStatement(query);
//			rs = pstmt.executeQuery();
//
//			while(rs.next()) {
//				EmpVO empVO = new EmpVO();
//				for(String column : columns_q) {
//					String type = column.split(",")[0];
//					String col = column.split(",")[1];
//					
//					//좀만 더하면 될것 같은데 여튼 안됨
//					
//					Sting colM = col.substring(0, 1) + col.substring(1);
//					
//					
//					if(type.equalsIgnoreCase("int")) {
//						Method method = empVO.getClass().getDeclaredMethod("setEno", Integer.class);
//						method.invoke(empVO, rs.getInt(col));
////						empVO.setEno(rs.getInt(col));
//					}
//						
//					else if(type.equalsIgnoreCase("str")) {
//						Method method = empVO.getClass().getDeclaredMethod("setEname", String.class);
//						
//						method.invoke(empVO, rs.getString(col));
////						empVO.setEno(rs.getSting(col));
//					}
//						
//					
//				}
//				
//				
//				list.add(empVO);
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			
//			try {
//				if(rs != null)
//					rs.close();
//				if(pstmt != null)
//					pstmt.close();
//				if(rs != null)
//					rs.close();
//			}catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}
//		
//		if(list.size() > 0)
//			return list;
//		else
//			return null;
//	}
	
	
	
	public List<EmpVO> getResult2(List<String> columns_q, String from_q, String condition_q){
			
		String columns_Q = "SELECT ";
		for(int i=0; i<columns_q.size(); i++) {
			if(i < columns_q.size()-1)
				columns_Q += columns_q.get(i) + ", ";
			else 
				columns_Q += columns_q.get(i) + " ";
		}
		from_q = "FROM " + from_q + " ";
		String query = columns_Q + from_q + condition_q;
		
		List<EmpVO> list = new ArrayList<EmpVO>();
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				EmpVO empVO = new EmpVO();
				for(String column : columns_q) {
					if(column.equalsIgnoreCase("eno"))
						empVO.setEno(rs.getInt("eno"));
					else if(column.equalsIgnoreCase("ename"))
						empVO.setEname(rs.getString("ename"));
					else if(column.equalsIgnoreCase("job"))
						empVO.setJob(rs.getString("job"));
					else if(column.equalsIgnoreCase("manager"))
						empVO.setManager(rs.getInt("manager"));
					else if(column.equalsIgnoreCase("hiredate"))
						empVO.setHireDate(rs.getString("hiredate"));
					else if(column.equalsIgnoreCase("salary"))
						empVO.setSalary(rs.getInt("salary"));
					else if(column.equalsIgnoreCase("commission"))
						empVO.setCommission(rs.getInt("commission"));
					else if(column.equalsIgnoreCase("dno"))
						empVO.setDno(rs.getInt("dno"));
					else if(column.equalsIgnoreCase("salaryplus"))
						empVO.setSalaryPlus(rs.getInt("salaryplus"));
				}
				
				
				list.add(empVO);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(rs != null)
					rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		if(list.size() > 0)
			return list;
		else
			return null;
	}
	
	public List<EmpVO> getResult(String query , boolean plusUse){
		
		List<EmpVO> list = new ArrayList<EmpVO>();
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				EmpVO empVO = new EmpVO();
				empVO.setEno(rs.getInt("eno"));
				empVO.setEname(rs.getString("ename"));
				empVO.setJob(rs.getString("job"));
				empVO.setManager(rs.getInt("manager"));
				empVO.setHireDate(rs.getString("hiredate"));
				empVO.setSalary(rs.getInt("salary"));
				empVO.setCommission(rs.getInt("commission"));
				empVO.setDno(rs.getInt("dno"));
				
				if(plusUse)
					empVO.setSalaryPlus(rs.getInt("salaryplus"));
				list.add(empVO);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(rs != null)
					rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		if(list.size() > 0)
			return list;
		else
			return null;
	}
	
	public List<EmpVO> exam_01(){
		String query ="SELECT ename, salary, "
				+ "salary+300 as salaryPlus "
				+ "FROM employee";
		
		List<EmpVO> list = new ArrayList<EmpVO>();
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			EmpVO empVO = null;
			while(rs.next()) {
				empVO = new EmpVO();
				empVO.setEname(rs.getString("ename"));
				empVO.setSalary(rs.getInt("salary"));
				empVO.setSalaryPlus(rs.getInt("salaryplus"));
				list.add(empVO);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(rs != null)
					rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		return list;
	}
	
	//메소드 정의
}
