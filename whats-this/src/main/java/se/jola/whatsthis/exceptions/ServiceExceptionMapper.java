package se.jola.whatsthis.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

    @Override
    public Response toResponse(ServiceException exception) {
	
	return Response.status(Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
    }

}
