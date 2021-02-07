package com.empmanager.model;

public class Model_Employees {
	
	
	private int eno;
	private double salary;
	private String ename;
	
	public Model_Employees() {
		
	}

	public Model_Employees(double salary, String ename) {
		super();
		this.salary = salary;
		this.ename = ename;
	}

	public Model_Employees(int eno, double salary, String ename) {
		super();
		this.eno = eno;
		this.salary = salary;
		this.ename = ename;
	}

	public int getEno() {
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	
}
