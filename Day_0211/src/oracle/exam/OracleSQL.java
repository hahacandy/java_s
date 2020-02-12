package oracle.exam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import oracle.db.OracleConn;

public class OracleSQL extends OracleConn {

	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//전체 검색
	public void printDeptList() throws Exception{
		String query ="SELECT * FROM department";
		stmt = myConn.createStatement();
		rs = stmt.executeQuery(query);
		
		while(rs.next()) {
			int dno = rs.getInt("dno");
			String dname = rs.getString("dname");
			String loc = rs.getString("loc");
			
			System.out.print(dno + "\t");
			System.out.print(dname + "\t");
			System.out.println(loc);
		}
	}
	
	//부서번호
	public void printSearchDno(int qdno) throws Exception{
		String query ="SELECT * FROM department where dno=?";
		pstmt = myConn.prepareStatement(query);
		pstmt.setInt(1, qdno);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int dno = rs.getInt("dno");
			String dname = rs.getString("dname");
			String loc = rs.getString("loc");
			
			System.out.print(dno + "\t");
			System.out.print(dname + "\t");
			System.out.println(loc);
		}
	}
	
	//employee 해당 부서번호 사람들을 출력
	public void printSearchInEmployee(int qdno) throws Exception{
		String query ="SELECT * FROM employee where dno=?";
		pstmt = myConn.prepareStatement(query);
		pstmt.setInt(1, qdno);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int eno = rs.getInt("eno");
			String ename = rs.getString("ename");
			String job = rs.getString("job");
			int manager = rs.getInt("manager");
			String hiredate = rs.getString("hiredate");
			int salary = rs.getInt("salary");
			int commission = rs.getInt("commission");
			int dno = rs.getInt("dno");
			
			
			System.out.print(eno + "\t");
			System.out.print(ename + "\t");
			System.out.print(job + "\t");
			System.out.print(manager + "\t");
			System.out.print(hiredate + "\t");
			System.out.print(salary + "\t");
			System.out.print(commission + "\t");
			System.out.println(dno);
		}
	}
	
	//입력 날짜 이후에 입사한 사원들
	public void printDateSearchInEmployee(String date) throws Exception{
		String query ="SELECT * FROM employee where HIREDATE>=?";
		pstmt = myConn.prepareStatement(query);
		pstmt.setString(1, date);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int eno = rs.getInt("eno");
			String ename = rs.getString("ename");
			String job = rs.getString("job");
			int manager = rs.getInt("manager");
			String hiredate = rs.getString("hiredate");
			int salary = rs.getInt("salary");
			int commission = rs.getInt("commission");
			int dno = rs.getInt("dno");
			
			
			System.out.print(eno + "\t");
			System.out.print(ename + "\t");
			System.out.print(job + "\t");
			System.out.print(manager + "\t");
			System.out.print(hiredate + "\t");
			System.out.print(salary + "\t");
			System.out.print(commission + "\t");
			System.out.println(dno);
		}
	}
	
	//이름 검색
	public void printNameSearch(String name) throws Exception{
		String query="SELECT eno, ename, job, hiredate FROM employee "
				+ "WHERE ename LIKE ?";
		pstmt = myConn.prepareStatement(query);
		pstmt.setString(1, "%"+name+"%");
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int eno = rs.getInt("eno");
			String ename = rs.getString("ename");
			String job = rs.getString("job");
			String hiredate = rs.getString("hiredate").substring(0,10);
			
			System.out.print(eno + "\t");
			System.out.print(ename + "\t");
			System.out.print(job + "\t");
			System.out.println(hiredate);
		}
		
	}
	
	@Override
	public void cleanUp() throws Exception {
		if(rs!=null)
			rs.close();
		if(stmt!=null)
			stmt.close();
		if(pstmt!=null)
			pstmt.close();
	}

}
