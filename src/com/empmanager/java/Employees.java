package com.empmanager.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.empmanager.dao.Employee_DAO;
import com.empmanager.model.Model_Employees;

public class Employees {

	public static void main(String[] args) {
		
		
		Scanner scan = new Scanner(System.in);
		
//		input commands
		System.out.println("Select number to perform operations\ncreate   = 1\nretrieve = 2\nupdate   = 3\ndelete   = 4\ntable    = 5");
		int operationNum = scan.nextInt();
		int eno;
		double salary;
		String ename, temp;
		Model_Employees employee = new Model_Employees();
		
		
//		JDBC Operation
		try {
			
//			create condition
			if (operationNum == 1) {
				
				eno = Employee_DAO.maxEno(employee);
				
				temp = scan.nextLine(); 					// This line is using because after Int, nextLine has been skipped in java
				
				System.out.println("Enter Employee Name");
				ename = scan.nextLine();
				
				System.out.println("Enter Employee Salary");
				salary = scan.nextDouble();
				
				employee.setEno(eno);
				employee.setEname(ename);
				employee.setSalary(salary);
				
				int i = Employee_DAO.insert(employee);
				
				if (i == 1) {
					System.out.println("Employee Added");
				} else {
					System.out.println("Cannot able to add the employee");
				}
				
			}
			
//			retrieve condition
			else if (operationNum == 2) {

				System.out.println("Enter Employee Number");
				eno = scan.nextInt();
				
				employee = Employee_DAO.retrieve(eno);
				
				System.out.println("Employee No.    :" + employee.getEno());
				System.out.println("Employee Name   :" + employee.getEname());
				System.out.println("Employee Salary :" + employee.getSalary());
				
			}
			
//			update condition
			else if (operationNum == 3) {
				
				System.out.println("Select number to perform operations\nUpdate Employee Name   = 1\nUpdate Employee Salary = 2");
				
				int updateNum = scan.nextInt();
				
//				update employee name
				if (updateNum == 1) {

					System.out.println("Enter Employee Number");
					eno = scan.nextInt();
					
					temp = scan.nextLine(); // This line is using because after Int, nextLine has been skipped in java
					
					System.out.println("Enter Employee Name");
					ename = scan.nextLine();
					
					employee.setEno(eno);
					employee.setEname(ename);
					
					int i = Employee_DAO.updateName(employee);
					
					if (i == 1) {
						System.out.println("Employee's Name Updated");
					} else {
						System.out.println("Failed");
					}
					
				}
//				update employee salary
				else if (updateNum == 2) {

					System.out.println("Enter Employee Number");
					eno = scan.nextInt();
					
					System.out.println("Enter Employee Salary");
					salary = scan.nextInt();
					
					employee.setEno(eno);
					employee.setSalary(salary);
					
					int i = Employee_DAO.updateSalary(employee);

					if (i == 1) {
						System.out.println("Employee's Salary Updated");
					} else {
						System.out.println("Failed");
					}
					
				}
				
//				invalid input
				else {
					System.out.println("Invalid Input");
				}
				
			} 
			
//			delete condition
			else if (operationNum == 4) {
				
				System.out.println("Enter Employee Number");
				eno = scan.nextInt();
				
				employee.setEno(eno);
				
				int i = Employee_DAO.delete(employee);
				
				if (i == 1) {
					System.out.println("Employee Deleted");
				} else {
					System.out.println("Employee not found");
				}
				
			}
			
//			select complete table
			else if (operationNum == 5) {
				List<Model_Employees> employees = Employee_DAO.retrieve_table();
				System.out.println("_______________________________________________");		// for padding
				System.out.printf("|%-3s| %-25s| %-9s\n", "Eno", "Employee Name", "Salary");
				System.out.println("+---+--------------------------+---------------");		// for padding
				for (Model_Employees emp:employees) {
					System.out.printf("|%03d| %-25s| %08.2f\n", emp.getEno(), emp.getEname(), emp.getSalary());	// printf is for padding
					System.out.println("+---+--------------------------+---------------");		// for padding
				}
				System.out.println("x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x");		// for padding
			}
			
//			invalid input
			else {
				System.out.println("Invalid Input");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
}