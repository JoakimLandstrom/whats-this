package se.jola.whatsthis.resource;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.jola.whatsthis.exceptions.ServiceException;
import se.jola.whatsthis.model.LocationRequestBean;
import se.jola.whatsthis.service.PoiService;

@Component
@Path("pois")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public final class PoiResource {

    @Autowired
    PoiService service;

    @GET
    public Response getClosestPois(@BeanParam LocationRequestBean location) {

	try {
	    String response = service.getClosestPois(location.getLat(), location.getLng());

	    return Response.ok(response).build();

	} catch (ServiceException e) {

	    return Response.status(Status.NOT_FOUND).build();
	}
    }

}
