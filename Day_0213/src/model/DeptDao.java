package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeptDao {
	DBManager manager = DBManager.getInstance();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	
	public int deleteDept(int dno) {
		int row=0;
		String query = "DELETE FROM department WHERE dno=?;";
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dno);
			row = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)
					pstmt.close();
				if(conn != null)
					conn.close();	
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return row;
	}
	
	public int updateDept(int dno, String dname, String loc) {
		int row=0;
		
		String query = "UPDATE department SET dname=?, loc=? WHERE dno=?";
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dname);
			pstmt.setString(2, dname);
			pstmt.setInt(3, dno);
			row = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return row;
	}
	
	public int insertDept(int dno, String dname, String loc) {

		int row = 0;
		String query = "INSERT INTO department VALUES(?,?,?)";
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dno);
			pstmt.setString(2, dname);
			pstmt.setString(3, loc);
			row = pstmt.executeUpdate();
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
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return row;
	}
	
	
	public List<DeptVO> selectAllDept(){
		String query = "SELECT * FROM department";
		
		List<DeptVO> deptVOs = selectQuery(query, null);
		
		return deptVOs;
	}
	
	public List<DeptVO> selectWhereDept(int dno) {
		String query = "SELECT * FROM department WHERE dno=?";
		
		List<String> wheres = new ArrayList<String>();
		wheres.add("dno,"+String.valueOf(dno));
		
		List<DeptVO> deptVOs = selectQuery(query, wheres);
		
		return deptVOs;
	}
	
	public List<DeptVO> selectQuery(String query, List<String> wheres) {
		List<DeptVO> deptVOs = new ArrayList<DeptVO>();
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			if(wheres != null) {
				for(String where : wheres) {
					String[] col = where.split(",");
					if(col[0].equalsIgnoreCase("dno"))
						
						pstmt.setInt(1, Integer.valueOf(col[1]));
				}
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DeptVO deptVO = new DeptVO();
				deptVO.setDno(rs.getInt("dno"));
				deptVO.setDname(rs.getString("dname"));
				deptVO.setLoc(rs.getString("loc"));
				deptVOs.add(deptVO);
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
		
		if(deptVOs.size() > 0) {
			return deptVOs;
		}else {
			return null;
		}
	}
	
	
}









