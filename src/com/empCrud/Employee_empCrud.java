package com.empCrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Employee_empCrud {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
//		input commands
		System.out.println("Select number to perform operations\ncreate   = 1\nretrieve = 2\nupdate   = 3\ndelete   = 4");
		int operationNum = scan.nextInt();
		int eno, salary;
		String ename, temp;
		
		
//		JDBC Operation
		try {
			
//			register driver with DriverManager
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			get Connection
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			
//			create condition
			if (operationNum == 1) {
				
				System.out.println("Enter Employee Number");
				eno = scan.nextInt();
				
				temp = scan.nextLine(); 					// This line is using because after Int, nextLine has been skipped in java
				
				System.out.println("Enter Employee Name");
				ename = scan.nextLine();
				
				System.out.println("Enter Employee Salary");
				salary = scan.nextInt();
				
				createEmp(connection, eno, ename, salary);
			}
			
//			retrieve condition
			else if (operationNum == 2) {

				System.out.println("Enter Employee Number");
				eno = scan.nextInt();
				
				retrieveEmp(connection, eno);
			}
			
//			update condition
			else if (operationNum == 3) {
				
				System.out.println("Update Employee Number = 1\nUpdate Employee Name = 2\nUpdate Employee Salary = 3\nUpdate All Details = 4\n");
				int updateNum = scan.nextInt();
				
//				update employee number
				if (updateNum == 1) {

					System.out.println("Enter Employee Number");
					eno = scan.nextInt();
					
					updateENO(connection, eno);
				}
//				update employee name
				else if (updateNum == 2) {

					System.out.println("Enter Employee Name");
					ename = scan.nextLine();
					
					updateENAME(connection, ename);
				}
//				update employee salary
				else if (updateNum == 3) {

					System.out.println("Enter Employee Salary");
					salary = scan.nextInt();
					
					updateESALARY(connection, salary);
				}
//				update all details
				else {

					System.out.println("Enter Employee Number");
					eno = scan.nextInt();
					
					temp = scan.nextLine(); 					// This line is using because after Int, nextLine has been skipped in java
					
					System.out.println("Enter Employee Name");
					ename = scan.nextLine();
					
					System.out.println("Enter Employee Salary");
					salary = scan.nextInt();
					
					updateEmp(connection, eno, ename, salary);
				}
				
			} 
			
//			delete condition
			else if (operationNum == 4) {
				
				System.out.println("Enter Employee Number");
				eno = scan.nextInt();
				
				deleteEmp(connection, eno);
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
//	method for create employee
	public static void createEmp(Connection connection, int eno, String ename, int salary) {
		
		try {
			
//			get statement object
			PreparedStatement preparedStatement = connection.prepareStatement("insert into emp values (?, ?, ?)");
			
//			set the values
			preparedStatement.setInt(1, eno);
			preparedStatement.setString(2, ename);
			preparedStatement.setInt(3, salary);
			
//			execute the query
			int i = preparedStatement.executeUpdate();
			
//			close the connection
			connection.close();
			
//			confirmation message
			if (i == 1) {
				System.out.println("Employee Added");
			} else {
				System.out.println("Failed");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

//	method for retrieve employee
	public static void retrieveEmp(Connection connection, int eno) {
		
		try {
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

//	method for update employee all details
	public static void updateEmp(Connection connection, int eno, String ename, int salary) {
		
		try {
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
//	method to update employee number
	public static void updateENO(Connection connection, int eno) {
		
	}
	
//	method to update employee name
	public static void updateENAME(Connection connection, String ename) {
		
	}
	
//	method to update employee salary
	public static void updateESALARY(Connection connection, int salary) {
		
	}
	
//	method for delete employee
	public static void deleteEmp(Connection connection, int eno) {
		
		try {
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
