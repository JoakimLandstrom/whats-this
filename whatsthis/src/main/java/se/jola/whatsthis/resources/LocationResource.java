package se.jola.whatsthis.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jola.whatsthis.models.LocationRequestBean;
import se.jola.whatsthis.services.LocationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
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
