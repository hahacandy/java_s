package shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import library.db.DBManager;
import shopping.vo.ShoppingVO;

public class ShoppingDao {
	DBManager manager = DBManager.getInstance();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//회원 등록
	public int regMemberQuery(List<String> infos) {
		int row = 0;
		String query = "INSERT INTO tbl_member_20200219(custno, custName, phone, gender, joindate,grade) VALUES(?,?,?,?,SYSDATE,?)"; 
		row = query(query, infos);
		return row;
	}
	
	//회원 수정
	public int modiMember(int custNo, List<String> infos) {
		int row = 0;
		String query = "UPDATE tbl_member_20200219 SET custname=?, phone=?, gender=?, grade=? WHERE custno="+custNo;
		row = query(query, infos);
		return row;
	}
	
	//회원삭제
	public int deleteMember(int custNo){
		int row = 0;
		String query = "DELETE FROM tbl_member_20200219 WHERE custno="+custNo;
		row = query(query, null);
		return row;
	}

	//일반 쿼리
	private int query(String query, List<String> options) {
		int row = 0;
		String[] option = null;
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			
			if(options != null) {
				for(int i=1; i<=options.size(); i++) {
					option = options.get(i-1).split(",");
					if(option[0].equalsIgnoreCase("str")) {
//						System.out.println(option[1]);
						pstmt.setString(i, option[1]);
					}
						
					else if(option[0].equalsIgnoreCase("int"))
						pstmt.setInt(i, Integer.valueOf(option[1]));
					else 
						System.out.println("일반 쿼리 오류:" + i + " 번째 옵션");
				}
			}
			row = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeDB();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	//모든 회원 다 불러오기
	public List<ShoppingVO> getAllMember(){
		String query = "SELECT * FROM tbl_member_20200219 ORDER BY custno";
		List<ShoppingVO> list = selectQuery(query, null);
		return list;
	}
	
	//회원번호에 맞는 한명 불러오기
	public List<ShoppingVO> getMember(int custno){
		String query = "SELECT * FROM tbl_member_20200219 WHERE custno="+custno;
		List<ShoppingVO> list = selectQuery(query, null);
		return list;
	}
	
	//셀렉트 쿼리
	private List<ShoppingVO> selectQuery(String query, List<String> options) {
		List<ShoppingVO> shoppingVOs = new ArrayList<ShoppingVO>();
		String[] option = null;
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			
			
			if(options != null) {
				for(int i=1; i<=options.size(); i++) {
					option = options.get(i-1).split(",");
					if(option[0].equalsIgnoreCase("str"))
						pstmt.setString(i, option[1]);
					else if(option[0].equalsIgnoreCase("int"))
						pstmt.setInt(i, Integer.valueOf(option[1]));
					else 
						System.out.println("쿼리 셀렉트 오류:" + i + " 번째 옵션");
				}
			}
			

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ShoppingVO shoppingVO = new ShoppingVO();
				try {
					shoppingVO.setCustNo(rs.getInt("custno"));
					shoppingVO.setCustName(rs.getString("custname"));
					shoppingVO.setPhone(rs.getString("phone"));
					shoppingVO.setGender(rs.getString("gender"));
					shoppingVO.setJoinDate(rs.getString("joindate"));
					shoppingVO.setGrade(rs.getString("grade"));
				}catch (Exception e) {}

				shoppingVOs.add(shoppingVO);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeDB();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if(shoppingVOs.size() > 0) {
			return shoppingVOs;
		}else {
			return null;
		}
	}
	
	private void closeDB() throws Exception{
		if(rs != null)
			rs.close();
		if(pstmt != null)
			pstmt.close();
		if(conn != null)
			conn.close();
	}
	
}
