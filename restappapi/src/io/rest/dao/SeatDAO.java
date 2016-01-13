package io.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import io.rest.connect.DB1connect;
import io.rest.exception.AppException;
import io.rest.model.Seats;

public class SeatDAO {

	public List<Seats> assign(int id) throws AppException, NotFoundException {
		List<Seats> s = new ArrayList<Seats>();
		Connection con = DB1connect.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("UPDATE seats SET CUST_ID = ? WHERE CUST_ID = NULL");
			ps.setInt(1,  id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Seats sa = new Seats();
				sa.setCust_id(rs.getInt("CUST_ID"));
				s.add(sa);
				
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
	
		
		return s;
}
		
		
		
		
	}

	

	

	

