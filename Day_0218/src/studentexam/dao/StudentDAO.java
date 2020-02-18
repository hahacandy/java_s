package studentexam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import studentexam.db.DBManager;
import studentexam.vo.StudentVO;

public class StudentDAO {
	DBManager manager = DBManager.getInstance();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	//아이디 중복하는 체크 있는 경우  true 반환
	public boolean isCntQuery(String column, String value) {
		
		String query = "SELECT COUNT("+ column +") AS count FROM student WHERE " + column + "=?";
		
		int cnt = 0; 
		
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.valueOf(value));
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
	
	//학생 정보 입력
	public int regStudent(List<String> infos) {
		int row = 0;
		String query = "INSERT INTO student(student_bun,student_name,student_addr,student_email,student_birth) VALUES(?, ?, ?, ?, TO_DATE(?,'YYYY-MM-DD'))";
		row = query(query, infos);
		return row;
	}
	
	//학생 정보 변경
	public int modifyStudent(int hakbun, List<String> infos) {
		int row = 0;

		String query = "UPDATE student SET student_name=?, student_addr=?, student_email=?, student_birth=TO_DATE(?,'YYYY-MM-DD') WHERE student_bun="+hakbun;

		row = query(query, infos);
		
		return row;
	}
	
	//학생 삭제 쿼리
	public int deleteStudent(int hakbun) {
		int row = 0;

		String query = "DELETE FROM student WHERE student_bun=" + hakbun;

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
	
	
	// 모든 학생 출력 쿼리
	public List<StudentVO> getAllStudents(){
		String query = "SELECT * FROM student";
		
		List<StudentVO> studentVOs = selectQuery(query, null);
		
		return studentVOs;
	}
	
	//학생 검색 쿼리
	public List<StudentVO> searchStudents(String value, String value2){
		List<String> info = new ArrayList<String>();
		String column = null;
		
		if(value.equals("이름")) {
			column = "student_name";
			info.add("str,"+value2);
		}else if(value.equals("학번")) {
			info.add("int,"+value2);
			column = "student_bun";
		}else if(value.equals("학년")) {
			info.add("str,"+value2);
			column = "SUBSTR(student_bun,1, 1)";
		}else {
			System.out.println("searchStudent2 value 값 오류!");
		}
		
		String query = "SELECT * FROM student WHERE "+column+"=?";
		
		List<StudentVO> studentVOs = selectQuery(query, info);
		
		return studentVOs;
	}
		
	//셀렉트 쿼리
		private List<StudentVO> selectQuery(String query, List<String> options) {
			List<StudentVO> studentVOs = new ArrayList<StudentVO>();
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
					StudentVO studentVO = new StudentVO();
					try {
						studentVO.setHakbun(rs.getInt("student_bun"));
						studentVO.setName(rs.getString("student_name"));
						studentVO.setAddr(rs.getString("student_addr"));
						studentVO.setEmail(rs.getString("student_email"));
						studentVO.setBirth(rs.getString("student_birth"));
					}catch (Exception e) {}

					studentVOs.add(studentVO);
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
			
			if(studentVOs.size() > 0) {
				return studentVOs;
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
