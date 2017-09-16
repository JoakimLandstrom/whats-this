package se.jola.whatsthis.resources;


import com.google.gson.Gson;
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
    private LocationService service;

    @GET
    public Response getClosestPois(@BeanParam LocationRequestBean location) {

        return Response.ok(new Gson().toJson(service.getLocations(location.getLat(), location.getLng()))).build();
    }

}
