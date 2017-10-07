package se.jola.whatsthis.resources;

import org.springframework.stereotype.Service;
import se.jola.whatsthis.models.CreateUserRequestBean;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
@Path("login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public final class UserResource {

    @POST
    public Response createNewUser(CreateUserRequestBean createUserRequestBean){


        return Response.ok().build();
    }
}
