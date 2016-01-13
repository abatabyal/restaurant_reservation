package io.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import io.rest.model.Reserve;
import io.rest.connect.DB1connect;
import io.rest.exception.AppException;

public class CustomerDAO {

	public Reserve create(Reserve res) throws AppException{
		
		Connection con = DB1connect.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("INSERT INTO customers (FIRST_NAME, LAST_NAME, EMAIL, PHONE, DATE_TIME, SIZE, REQUEST) VALUES (?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,  res.getFirstname());
			ps.setString(2, res.getLastname());
			ps.setString(3, res.getEmail());
			ps.setString(4, res.getPhone());
			ps.setString(5, res.getDate_time());
			ps.setInt(6, res.getSize());
			ps.setString(7, res.getRequest());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				
				res.setId(rs.getInt(1));
				
			}
			
		} catch (SQLException e) {
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
	
		
		return res;
	
			
}

	public Reserve update(int id, Reserve res) throws AppException, NotFoundException{
		
		Connection con = DB1connect.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("UPDATE customers SET FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, PHONE = ?, DATE_TIME = ?, SIZE = ?, REQUEST = ? WHERE ID = ?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,  res.getFirstname());
			ps.setString(2, res.getLastname());
			ps.setString(3, res.getEmail());
			ps.setString(4, res.getPhone());
			ps.setString(5, res.getDate_time());
			ps.setInt(6, res.getSize());
			ps.setString(7, res.getRequest());
			
			ps.setInt(8,  id);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			

						
		} catch (SQLException e) {
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
	
		
		return res;
	}

	public void delete(int id) throws AppException {
		
		Connection con = DB1connect.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("DELETE FROM customers WHERE ID =?");
			ps.setInt(1,  id);
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
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
			
		
		
	}

	public List<Reserve> listall() throws AppException {
		
		List<Reserve> resa = new ArrayList<Reserve>();
		Connection con = DB1connect.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM customers");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Reserve res = new Reserve();
				res.setId(rs.getInt("ID"));
				res.setFirstname(rs.getString("FIRST_NAME"));
				res.setLastname(rs.getString("LAST_NAME"));
				res.setEmail(rs.getString("EMAIL"));
				res.setPhone(rs.getString("PHONE"));
				res.setDate_time(rs.getString("DATE_TIME"));
				res.setSize(rs.getInt("SIZE"));
				res.setRequest(rs.getString("DATE_TIME"));
				
				resa.add(res);
			}
		} catch (SQLException e) {
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
	
		
		return resa;
	}
	}

