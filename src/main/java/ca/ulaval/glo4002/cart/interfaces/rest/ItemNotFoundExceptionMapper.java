package ca.ulaval.glo4002.cart.interfaces.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import ca.ulaval.glo4002.cart.application.ItemNotFoundException;

public class ItemNotFoundExceptionMapper implements ExceptionMapper<ItemNotFoundException> {

	@Override
	public Response toResponse(ItemNotFoundException arg0) {
		return Response.status(Status.NOT_FOUND).build();
	}

}
