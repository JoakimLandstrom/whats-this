package se.jola.whatsthis.resource;

import javax.ws.rs.PathParam;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.jola.whatsthis.model.LocationRequestBean;
import se.jola.whatsthis.service.LocationService;

@Component
@Path("locations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public final class LocationResource {

    @Autowired
    LocationService service;

    @GET
    public Response getClosestPois(@BeanParam LocationRequestBean location) {

	return Response.ok(service.getClosestPois(location.getLat(), location.getLng())).build();
    }

    @GET
    @Path("{name}")
    public Response getLocationInfo(@PathParam("name") String name) {

	return Response.ok(service.getPoiInfo(name)).build();
    }
}
