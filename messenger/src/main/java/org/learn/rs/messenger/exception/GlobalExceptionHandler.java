package org.learn.rs.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.learn.rs.messenger.model.ErrorMessage;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage("Something wrong happen");
		errorMessage.setStatus(500);
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
	}

}
