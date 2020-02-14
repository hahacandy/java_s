package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;

public class EmpDao {
	DBManager manager = DBManager.getInstance();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public int deleteQuery(int eno) {
		int row = 0;
		
		String query = "DELETE FROM employee WHERE eno=?";
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, eno);
			row = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
	
		return row;
	}
	
	
	public int updateQuery(List<String> info) {
		int row = 0;
		
		String query = "UPDATE employee SET ename=?, job=?, manager=?, hiredate=TO_DATE(?,'YYYY-MM-DD'), salary=?, commission=? WHERE eno=?";
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			for(int i=1; i<info.size();i++) {
				String[] in = info.get(i).split(",");
				if(in[0].equalsIgnoreCase("int"))
				{
					pstmt.setInt(i, Integer.valueOf(in[1]));
				}
				else if(in[0].equalsIgnoreCase("str"))
					pstmt.setString(i, in[1]);
				else {
					System.out.println("updateQuery " + i + "번째 인자값 오류");
				}
					
			}
			
			pstmt.setInt(7, Integer.valueOf(info.get(0)));
			
			row = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
	
		return row;
	}
	
	
	//사원 신규가입
	public int insertQuery(List<String> info) {
		int row = 0;
		
		String query = "INSERT INTO employee(eno,ename,job,manager,hiredate,salary,commission, dno) VALUES(CUSTOM_SEQ_NUM.NEXTVAL,?,?,?,?,?,?,?)";
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			for(int i=1; i<=info.size();i++) {
				if(i==1 || i==2 || i==4)
					pstmt.setString(i, info.get(i-1));
				else if(i==3 || i==5 || i==6 || i==7)
					pstmt.setInt(i, Integer.valueOf(info.get(i-1)));
				else
					System.out.println("insertQuery " + i + "번째 인자값 오류");
			}
			
			row = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
	
		return row;
	}
	
	//사원이름으로 검색해서 해당 사원들의 리스트를 반납
	public List<EmpVO> selectSearchEmp(String ename) {
		List<EmpVO> EmpVOs = null;
		//해당 이름의 사원들을 검색
		String query = "SELECT e.*, d.dname  FROM employee e, department d WHERE e.ename ='"+ename+"' AND e.dno=d.dno";
		EmpVOs = selectQuery(query);
		if(EmpVOs != null) {
			for(EmpVO vo : EmpVOs) {
				String query2 = "SELECT * FROM employee WHERE eno=(SELECT manager FROM employee WHERE eno = '"+ vo.getEno() +"')";
				List<EmpVO> temp = selectQuery(query2); //상사가 있으면
				if(temp != null)
				{
					String managerName = temp.get(0).getEname();
					vo.setManager2(managerName);
				}
			}
		}
		
		return EmpVOs;
	}
	
	//사원전체리스트 반납
	public List<EmpVO> selectAllEmp(){
		String query = "SELECT e.*, dname FROM employee e, department d WHERE e.dno = d.dno";
		
		List<EmpVO> EmpVOs = selectQuery(query);
		
		return EmpVOs;
	}
	
	//사원번호로 해당 사원 정보 가지고옴
	public List<EmpVO> selectOneEmp(int eno){
		String query = "SELECT e.*, dname FROM employee e, department d WHERE e.eno="+ eno +" AND e.dno=d.dno";
		
		List<EmpVO> EmpVOs = selectQuery(query);
		
		return EmpVOs;
	}
	
	private List<EmpVO> selectQuery(String query) {
		List<EmpVO> list = new ArrayList<EmpVO>();
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpVO VO = new EmpVO();
				VO.setEno(rs.getInt("eno"));
				VO.setEname(rs.getString("ename"));
				VO.setJob(rs.getString("job"));
				VO.setManager(rs.getInt("manager"));
				VO.setHireDate(rs.getString("hiredate"));
				VO.setSalary(rs.getInt("salary"));
				VO.setCommission(rs.getInt("commission"));
				VO.setDno(rs.getInt("dno"));
				try {
					VO.setDname(rs.getString("dname"));
				} catch (Exception e) {}
				
				list.add(VO);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
//				System.out.println("db 정상 종료");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if(list.size() > 0) {
			return list;
		}else {
			return null;
		}
	}

}
