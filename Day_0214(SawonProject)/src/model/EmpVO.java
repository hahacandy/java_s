package model;

//사원정보 테이블

public class EmpVO {
	private int eno;
	private String ename;
	private String job;
	private int manager;
	private String hireDate;
	private int salary;
	private int commission;
	private int dno;
	
	private String dname;
	private String manager2;
	
	public String getManager2() {
		return manager2;
	}
	public void setManager2(String manager2) {
		this.manager2 = manager2;
	}
	
	public int getEno() {
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getManager() {
		return manager;
	}
	public void setManager(int manager) {
		this.manager = manager;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String date) {
		this.hireDate = date;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getCommission() {
		return commission;
	}
	public void setCommission(int commission) {
		this.commission = commission;
	}
	public int getDno() {
		return dno;
	}
	public void setDno(int dno) {
		this.dno = dno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	
}
