package se.jola.whatsthis.exceptions;

import com.google.gson.Gson;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ServiceExceptionMapper implements ExceptionMapper<ServiceException>{

    @Override
    public Response toResponse(ServiceException exception) {
        return Response.status(exception.getExceptionModel().getHttpStatus()).entity(new Gson().toJson(exception.getExceptionModel())).build();
    }
}
