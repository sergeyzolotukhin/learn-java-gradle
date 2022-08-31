package ua.in.sz.cxf.server;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SimpleExceptionMapper implements ExceptionMapper<IllegalStateException> {
    public Response toResponse(IllegalStateException ex) {
        return Response.status(Response.Status.BAD_REQUEST).
                entity("HOW TO GET A MESSAGE FROM AN EXCEPTION IN HERE???")
                .build();
    }
}