package se.jola.whatsthis.resources;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import se.jola.whatsthis.models.LocationRequest;
import se.jola.whatsthis.services.LocationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
@Path("locations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@CrossOrigin(origins = "http")
public final class LocationResource {

    @Autowired
    private LocationService service;

    @GET
    public Response getClosestPois(@BeanParam LocationRequest locationRequest) {

        return Response.ok(new Gson().toJson(service.getLocations(locationRequest))).build();
    }

}
