package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;

public class DeptDao {
	DBManager manager = DBManager.getInstance();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;


	public List<DeptVO> selectAllDept(){
		String query = "SELECT * FROM department";
		
		List<DeptVO> deptVOs = selectQuery(query, null);
		
		return deptVOs;
	}
	
	private List<DeptVO> selectQuery(String query, List<String> wheres) {
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
