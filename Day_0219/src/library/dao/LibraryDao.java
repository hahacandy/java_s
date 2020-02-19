package library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import library.db.DBManager;
import library.vo.LibraryVO;


public class LibraryDao {
	DBManager manager = DBManager.getInstance();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//책 반납
	public int returnReservation(int bun) {
		int row = 0;
		String query = "UPDATE reservation SET status=2 WHERE lentno="+bun;
		row = query(query, null);
		return row;
	}
	
	//책 대출 수정
	public int modiReservation(int bun, List<String> infos, boolean isInDate) {
		int row = 0;
		String query = null;
		
		if(isInDate)
			query = "UPDATE reservation SET custname=?, phone=?, bookno=?, outdate=?, indate=TO_DATE(?,'YYYYMMDD'), status='2' WHERE lentno="+bun;
		else
			query = "UPDATE reservation SET custname=?, phone=?, bookno=?, outdate=? WHERE lentno="+bun;
		
		System.out.println(infos);
		
		row = query(query, infos);
		return row;
	}
	
	//책 대출 입력
	public int regReservation(List<String> infos, boolean isInDate) {
		int row = 0;
		String query = null;
		if(isInDate)
			query = "INSERT INTO reservation(lentno, custname,phone,bookno,outdate,indate,status) VALUES(RESERVATION_SEQ.NEXTVAL, ?, ?, ?, ?, ?, '1')";
		else
			query = "INSERT INTO reservation(lentno, custname,phone,bookno,outdate,status) VALUES(RESERVATION_SEQ.NEXTVAL, ?, ?, ?, ?, '1')";
		
		row = query(query, infos);
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
	
	//모든 예약 다 가져옴
	public List<LibraryVO> getAllReservation(){
		List<LibraryVO> libraryVOs = null;
		
		String query = "SELECT * FROM reservation ORDER BY lentno";
		
		libraryVOs = selectQuery(query, null);
		
		return libraryVOs;
	}
	
	//해당 대출번호에 대한 예약 가져옴
	public List<LibraryVO> getReservation(int bun){
		List<LibraryVO> libraryVOs = null;
		
		String query = "SELECT * FROM reservation WHERE lentno="+bun;
		
		libraryVOs = selectQuery(query, null);
		
		return libraryVOs;
	}
	
	//셀렉트 쿼리
	private List<LibraryVO> selectQuery(String query, List<String> options) {
		List<LibraryVO> libraryVOs = new ArrayList<LibraryVO>();
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
				LibraryVO libraryVO = new LibraryVO();
				try {
					libraryVO.setBun(rs.getInt("lentno"));
					libraryVO.setName(rs.getString("custname"));
					libraryVO.setPhone(rs.getString("phone"));
					libraryVO.setCode(rs.getInt("bookno"));
					libraryVO.setOutDate(rs.getString("outdate"));
					libraryVO.setInDate(rs.getString("indate"));
					libraryVO.setStatus(rs.getString("status"));
				}catch (Exception e) {}

				libraryVOs.add(libraryVO);
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
		
		if(libraryVOs.size() > 0) {
			return libraryVOs;
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
