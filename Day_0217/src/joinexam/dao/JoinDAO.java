package joinexam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import joinexam.vo.JoinVO;


public class JoinDAO {

	joinexam.db.DBManager manager = joinexam.db.DBManager.getInstance();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	

	
	//회원가입 쿼리
	public int regMember(List<String> infos) {
		int row = 0;
		String query = "INSERT INTO join(userid,name,passwd,email,age) VALUES(?, ?, ?, ?, ?)";
		row = query(query, infos);
		return row;
	}
	
	//회원정보 수정 쿼리
	public int updateMember(String userid, List<String> infos) {
		int row = 0;
		
		String query = "UPDATE join SET name=?, passwd=?, email=?, age=? WHERE userid='" + userid + "'";
		row = query(query, infos);
		
		return row;
	}
	
	//회원 삭제 쿼리
	public int deleteMember(String userid) {
		int row = 0;
		
		String query = "DELETE FROM join WHERE userid='" + userid + "'";
		
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
					if(option[0].equalsIgnoreCase("str"))
						pstmt.setString(i, option[1]);
					else if(option[0].equalsIgnoreCase("int"))
						pstmt.setInt(i, Integer.valueOf(option[1]));
					else 
						System.out.println("쿼리 인설트 오류:" + i + " 번째 옵션");
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
	
	
	//아이디 중복하는 체크 있는 경우  true 반환
	public boolean isId(String userid) {
		
		String query = "SELECT COUNT(userid) AS count FROM join WHERE userid=?";
		
		int cnt = 0; 
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				cnt = rs.getInt("count"); //1이상이면 아이디 중복
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeDB();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return cnt > 0;
	}
	
	// 모든 멤버 출력 쿼리
	public List<JoinVO> selectAllJoin(){
		String query = "SELECT * FROM join";
		
		List<JoinVO> joinVOs = selectQuery(query, null);
		
		return joinVOs;
	}
	
	//로그인 쿼리
	public boolean login(String userid, String passwd, String[] name) {
		boolean result = false;
		List<String> info = new ArrayList<String>();
		
		String query = "SELECT userid, name FROM join WHERE userid=? AND passwd=?";
		info.add("str," + userid);
		info.add("str," + passwd);

		List<JoinVO> joinVOs = selectQuery(query, info);
		
		if(joinVOs != null)
		{
			name[0] = joinVOs.get(0).getName();
			return true;
		}
		else
			return false;
	}
	
	//아이디 또는 이름으로 검색 쿼리
	public List<JoinVO> search(String mode, String value){
		String query = null;
		List<String> info = new ArrayList<String>();
		
		if(mode.equalsIgnoreCase("id"))
			query = "SELECT * FROM join WHERE userid=?";
		else if(mode.equalsIgnoreCase("name"))
			query = "SELECT * FROM join WHERE name=?";
		
		info.add("str," + value);
		
		List<JoinVO> joinVOs = selectQuery(query, info);
		
		return joinVOs;
	}
	
	//셀렉트 쿼리
	private List<JoinVO> selectQuery(String query, List<String> options) {
		List<JoinVO> joinVOs = new ArrayList<JoinVO>();
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
				JoinVO joinVO = new JoinVO();
				try {
					joinVO.setUserid(rs.getString("userid"));
					joinVO.setName(rs.getString("name"));
					joinVO.setPasswd(rs.getString("passwd"));
					joinVO.setEmail(rs.getString("email"));
					joinVO.setAge(rs.getInt("age"));
					joinVO.setRegdate(rs.getString("regdate"));
				}catch (Exception e) {}

				joinVOs.add(joinVO);
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
		
		if(joinVOs.size() > 0) {
			return joinVOs;
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
