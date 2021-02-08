package com.empmanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.empmanager.model.Model_Employees;

public class Employee_DAO {
	
	protected static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
//	method to retrieve max employee number
	public static int maxEno(Model_Employees employee) {
		int eno = 100;
		try {
			Connection connection = Employee_DAO.getConnection();
			PreparedStatement ps = connection.prepareStatement("select max(eno) from emp");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				eno = rs.getInt(1);
				eno++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eno;
	}
	
//	method to create/insert employee
	public static int insert(Model_Employees employee) {
		int i = 0;
		try {
			Connection connection = Employee_DAO.getConnection();
			PreparedStatement ps = connection.prepareStatement("insert into emp values (?, ?, ?)");
			ps.setInt(1, employee.getEno());
			ps.setString(2, employee.getEname());
			ps.setDouble(3, employee.getSalary());
			i = ps.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
//	method to retrieve the employee
	public static Model_Employees retrieve(int eno_input) {
		Model_Employees employee = null;
		int eno = 0;
		String ename = null;
		double salary = 0.0;
		try {
			Connection connection = Employee_DAO.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from emp where eno = ?");
			ps.setInt(1, eno_input);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				eno = rs.getInt(1);
				ename = rs.getString(2);
				salary = rs.getDouble(3);
			} else {
				System.out.println("No Employee Found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		employee = new Model_Employees(eno, salary, ename);
		return employee;
	}

//	method to update name
	public static int updateName(Model_Employees employee) {
		int i = 0;
		try {
			Connection connection = Employee_DAO.getConnection();
			PreparedStatement ps = connection.prepareStatement("update emp set ename = ? where eno = ?");
			ps.setString(1, employee.getEname());
			ps.setInt(2, employee.getEno());
			i = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

//	method to update salary
	public static int updateSalary(Model_Employees employee) {
		int i = 0;
		try {
			Connection connection = Employee_DAO.getConnection();
			PreparedStatement ps = connection.prepareStatement("update emp set salary = ? where eno = ?");
			ps.setDouble(1, employee.getSalary());
			ps.setInt(2, employee.getEno());
			i = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
//	method to delete Employee
	public static int delete(Model_Employees employee) {
		int i = 0;
		try {
			
			int eno = Employee_DAO.maxEno(employee) - 1;
			
			if (eno == employee.getEno()) {
				Connection connection = Employee_DAO.getConnection();
				PreparedStatement ps = connection.prepareStatement("delete emp where eno = ?");
				ps.setInt(1, employee.getEno());
				i = ps.executeUpdate();
			} else {
				i = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
//	method to retrieve complete table the employee
	public static List<Model_Employees> retrieve_table() {
		
		List<Model_Employees> employees = new ArrayList<Model_Employees>();
		
		try {
			Connection connection = Employee_DAO.getConnection();
			PreparedStatement ps = connection.prepareStatement("select * from emp");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Model_Employees employee = new Model_Employees();
				
				employee.setEno(rs.getInt(1));
				employee.setEname(rs.getString(2));
				employee.setSalary(rs.getDouble(3));
				
				employees.add(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employees;
	}
	
}
