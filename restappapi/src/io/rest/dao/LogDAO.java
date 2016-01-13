package io.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.rest.exception.AppException;
import io.rest.connect.DB1connect;
import io.rest.model.LoginAuth;



public class LogDAO {
	
	public Object login(String username, String password) throws AppException {
		String usr =" ";
		String pass = "";
	
	LoginAuth log = null;
	Connection con = DB1connect.connect();
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	try {
		ps = con.prepareStatement("SELECT * FROM owner WHERE USERNAME = ? && PASSWORD = ? ");
		ps.setString(1,  username);
		ps.setString(2,  password);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			log = new LoginAuth();
			 usr = log.setUsername(rs.getString("USERNAME"));
			 pass =log.setPassword(rs.getString("PASSWORD"));
		}
		
		if (username.equals(usr) && password.equals(pass)) {
	        System.out.println("Successful Login!\n----");
	    } else {
	        System.out.println("Incorrect Password\n----");
	    }
	}
	 catch (SQLException e) {
		e.printStackTrace();
		throw new AppException(e.getMessage());
	}
	finally {
		
		try {
			
			if(ps !=null) {
				ps.close();
			}
			
			if(rs != null) {
				rs.close();
			}
			if (con != null) {
				con.close();
			}
	
		} catch (SQLException e) {
		e.printStackTrace();
	}
}
	return rs;
	}
}


	
