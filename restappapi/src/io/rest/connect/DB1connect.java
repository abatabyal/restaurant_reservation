package io.rest.connect;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DB1connect {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/cust_db";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL JDBC driver loaded");
		} catch (ClassNotFoundException e) {
			System.out.println("Error Loading JDBC driver");
			e.printStackTrace();
		}
	}
	
	public static Connection connect() {
		Connection con = null;
		
		try {
			con = (Connection) DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
		} catch (SQLException e) {
			System.err.println("Error getting connection");
			e.printStackTrace();
		}
		
		
		return con;
	}

}