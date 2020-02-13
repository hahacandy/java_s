package customer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.DBManager;

public class CusDao {
	DBManager manager = DBManager.getInstance();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//시간없다 빨리 만들자
	public int insertQuery(int num, List<String> info) {
		int row = 0;
		
		String query = "INSERT INTO custom(num,name,tel,addr,office,birthday,sex) VALUES(?,?,?,?,?,?,?)";
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			for(int i=2; i<=info.size()+1;i++) {
				pstmt.setString(i, info.get(i-2));
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
	
	//수정용
	public int updateQuery(int num, List<String> info) {
		int row = 0;
		String query = "UPDATE custom SET name=?, tel=?, addr=?, office=?, birthday=TO_DATE(?,'YYYY-MM-DD'), sex=? WHERE num=?";
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			for(int i=1; i<=info.size();i++) {
				pstmt.setString(i, info.get(i-1));
			}
			pstmt.setInt(7, num);
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
	
	//select 와 delete 의 경우에만 씀
	public Object query(String DML, String from, List<String> wheres) {
		int row=0;
		List<CusVO> cusVOs = new ArrayList<CusVO>();
		
		DML = DML.toUpperCase();
		
		String query = DML + " FROM "+ from;
		
		if(wheres != null) {
			String where_q = "";
			for(int i=0; i<wheres.size(); i++) {
				String[] where = wheres.get(i).split(",");
				String where_q2 = where[1] + "=";
				if(where[0].equalsIgnoreCase("int"))
					where_q2 += where[2];
				else if(where[0].equalsIgnoreCase("str")){
					where_q2 += '\'' +where[2] + '\'';
				}
				
				if(i >= 1) {
					where_q += ", " + where_q2;
				}else {
					where_q = where_q2;
				}
			}
			
			query += " WHERE " + where_q;
		}

		
//		System.out.println(query); //테스트
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			if(DML.matches(".*SELECT.*")) {
				rs = pstmt.executeQuery();
				while(rs.next()) {
					CusVO cusVO = new CusVO();
					cusVO.setNum(rs.getInt("num"));
					cusVO.setName(rs.getString("name"));
					cusVO.setTel(rs.getString("tel"));
					cusVO.setAddr(rs.getString("addr"));
					cusVO.setOffice(rs.getString("office"));
					cusVO.setBirthday(rs.getString("birthday"));
					cusVO.setSex(rs.getString("sex"));
					cusVO.setInDate(rs.getString("indate"));
					cusVOs.add(cusVO);
				}
			}else {
				row = pstmt.executeUpdate();
			}
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
		
		if(DML.matches(".*SELECT.*")) {
			if(cusVOs.size() > 0) {
				return cusVOs;
			}else {
				return null;
			}
			
		}else if(DML.matches(".*DELETE.*")){
			return row;
		}else {
			return null;
		}
	}
	

}
