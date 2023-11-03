package ua.in.sz.cxf.server;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class SimpleExceptionMapper implements ExceptionMapper<IllegalStateException> {
    public Response toResponse(IllegalStateException ex) {
        return Response.status(Response.Status.BAD_REQUEST).
                entity("HOW TO GET A MESSAGE FROM AN EXCEPTION IN HERE??? " + ex.getMessage())
                .build();
    }
}