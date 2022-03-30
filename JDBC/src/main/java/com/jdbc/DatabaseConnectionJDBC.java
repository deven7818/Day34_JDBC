package com.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/**
 * 1.creating connection with dataBase payroll Service
 */
public class DatabaseConnectionJDBC{
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/payroll_service";
		String uname = "root";
		String pass = "Deven#7818";
		
		Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded");
		}catch(ClassNotFoundException ex) {
			throw new IllegalStateException("Cannot find the Driver in classpath");
		}
		driversList();
		
		try {
			System.out.println("Connecting to the database :" + url);
			connection = DriverManager.getConnection(url, uname, pass);
			System.out.println("Connection Successfull.." + connection);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
		private static void driversList() {
			Enumeration<Driver> driverList = DriverManager.getDrivers();
			while(driverList.hasMoreElements()) {
				Driver driverClass = driverList.nextElement();
				System.out.println(driverClass.getClass().getName());
			
		}
	}
}
