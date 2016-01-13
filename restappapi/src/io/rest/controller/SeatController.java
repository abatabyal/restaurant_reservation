package io.rest.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.rest.dao.CustomerDAO;
import io.rest.dao.SeatDAO;
import io.rest.exception.AppException;
import io.rest.model.Reserve;
import io.rest.model.Seats;

@Path("/seats")
public class SeatController {
	
	public List<Reserve> listall(){
		CustomerDAO dao = new CustomerDAO();
		List<Reserve> rese = null;
		
		try {
			rese = dao.listall();
		} catch (AppException e) {
			
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		} 
		
		return rese;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Seats> assign(@PathParam("id") int id) {
		SeatDAO dao = new SeatDAO();
		List<Seats> seat = null;
		try {
			
			seat = dao.assign(id);
			
		}catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		return seat;
	}	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Reserve create(Reserve res) throws AppException{
		CustomerDAO dao = new CustomerDAO();
		res = dao.create(res);
		return res;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Reserve update(@PathParam("id") int id, Reserve res) throws AppException {
		//where check whether it exists
		CustomerDAO dao = new CustomerDAO();
		res = dao.update(id, res);
		return res;
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id) throws AppException {
		
		CustomerDAO dao = new CustomerDAO();
		dao.delete(id);
		return Response.ok().build();
		
	}

}
