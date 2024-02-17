package ua.in.sz.pattern.spring.camel;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/hello")
public interface WebRsService {
    @GET
    @Produces(TEXT_PLAIN)
    @Path("/{text}")
    String sayHi(@PathParam("text") String text);
}
