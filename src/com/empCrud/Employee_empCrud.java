package com.empCrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Employee_empCrud {
	
//	main method
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
				
				System.out.println("Select number to perform operations\nUpdate Employee Name   = 1\nUpdate Employee Salary = 2");
//				System.out.println("");
				int updateNum = scan.nextInt();
				
//				update employee name
				if (updateNum == 1) {

					System.out.println("Enter Employee Number");
					eno = scan.nextInt();
					
					temp = scan.nextLine(); 					// This line is using because after Int, nextLine has been skipped in java
					
					System.out.println("Enter Employee Name");
					ename = scan.nextLine();
					
					updateENAME(connection, ename, eno);
				}
//				update employee salary
				else if (updateNum == 2) {

					System.out.println("Enter Employee Number");
					eno = scan.nextInt();
					
					System.out.println("Enter Employee Salary");
					salary = scan.nextInt();
					
					updateESALARY(connection, salary, eno);
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
				
				deleteEmp(connection, eno);
			}
			
//			invalid input
			else {
				System.out.println("Invalid Input");
			}
			
//			close connection
//			connection.close();
			
			
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
			
			PreparedStatement preparedStatement = connection.prepareStatement("select * from emp where eno = ?");
			
			preparedStatement.setInt(1, eno);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3));
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
//	method to update employee name
	public static void updateENAME(Connection connection, String ename, int eno) {
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("update emp set ename = ? where eno = ?");
					
				preparedStatement.setString(1, ename);
				preparedStatement.setInt(2, eno);
					
				int i = preparedStatement.executeUpdate();
					
				if (i == 1) {
					System.out.println("Employee Name is Updated");
				} else {
					System.out.println("Failed");
				}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
//	method to update employee salary
	public static void updateESALARY(Connection connection, int salary, int eno) {
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("update emp set salary = ? where eno = ?");
			
			preparedStatement.setInt(1, salary);
			preparedStatement.setInt(2, eno);
			
			int i = preparedStatement.executeUpdate();
			
			if (i == 1) {
				System.out.println("Employee Salary Updated");
			} else {
				System.out.println("Failed");
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
//	method for delete employee
	public static void deleteEmp(Connection connection, int eno) {
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("delete emp where eno = ?");
			
			preparedStatement.setInt(1, eno);
			
			int i = preparedStatement.executeUpdate();
			
			if (i == 1) {
				System.out.println("Employee Deleted");
			} else {
				System.out.println("Failed");
			}
 			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}