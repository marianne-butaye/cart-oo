package ca.ulaval.glo4002.cart.interfaces.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.cart.application.PersistenceException;

public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {

	@Override
	public Response toResponse(PersistenceException exception) {
		Logger.getGlobal().log(Level.SEVERE, exception.getMessage(), exception);
		return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
	}

}
