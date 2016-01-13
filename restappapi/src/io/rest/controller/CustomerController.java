package io.rest.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.rest.model.Reserve;
import io.rest.dao.CustomerDAO;
import io.rest.exception.AppException;

@Path("/reserve")
public class CustomerController {
	
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


