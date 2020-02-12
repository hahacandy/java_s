package oracle.dao;

import java.sql.*;
import java.util.*;

import db.DBManager;
import model.DeptVO;
import model.EmpVO;

public class SawonDAO {
	oracle.db.DBManager manager = oracle.db.DBManager.getInstance();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//부서 정보 테이블 전체 검색 메소드 정의
	public List<DeptVO> deptList() {
		String query = "SELECT * FROM department";
		//리턴 타입 정의
		List<DeptVO> list = new ArrayList<DeptVO>();
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DeptVO VO = new DeptVO();
				VO.setDno(rs.getInt("dno"));
				VO.setDname(rs.getString("dname"));
				VO.setLoc(rs.getString("loc"));
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	//부서 정보 테이블 전체 검색 메소드 정의
	public List<EmpVO> empList() {
		String query = "SELECT * FROM employee";
		//리턴 타입 정의
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public List<EmpVO> selectEmp(int dno, String job) {
			String query ="SELECT * FROM employee WHERE dno=? AND job=?";
			List<EmpVO> list = new ArrayList<EmpVO>();
			try {
				conn = manager.getConnection();
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, dno);
				pstmt.setString(2, job);
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
					
					list.add(empVO);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					
					if(rs != null)
						rs.close();
					if(pstmt != null)
						pstmt.close();
					if(conn != null)
						conn.close();
					
				}catch (Exception e){
					e.printStackTrace();
				}
				
			}
			
			return list;
	}
	
	public List<EmpVO> selectOREmp(int dno, String job){
		String query ="SELECT * FROM employee WHERE dno=? OR job=?";
		List<EmpVO> list = new ArrayList<EmpVO>();
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dno);
			pstmt.setString(2, job);
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
