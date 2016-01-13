package io.rest.login;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import io.rest.dao.LogDAO;
import io.rest.exception.AppException;
import io.rest.model.LoginAuth;

@Path("/login")
public class OwnerLogin {
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginAuth login(String username, String password) {
		
		LoginAuth logi = null;
		try {
			LogDAO dao = new LogDAO();
			logi = (LoginAuth) dao.login(username, password);
		}catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return logi;
		
		

	}
		
	}

