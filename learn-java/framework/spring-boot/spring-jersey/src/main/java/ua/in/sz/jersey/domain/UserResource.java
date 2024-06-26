package ua.in.sz.jersey.domain;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import ua.in.sz.jersey.domain.model.User;
import ua.in.sz.jersey.domain.model.Users;

@Path("/users")
public class UserResource {
    private static final Map<Integer, User> DB = new HashMap<>();

    @GET
    @Produces("application/json")
    public Users getAllUsers() {
        Users users = new Users();
        users.setUsers(new ArrayList<>(DB.values()));
        return users;
    }

    @POST
    @Consumes("application/json")
    public Response createUser(User user) throws URISyntaxException {
        if (user.getFirstName() == null || user.getLastName() == null) {
            return Response.status(400).entity("Please provide all mandatory inputs").build();
        }
        user.setId(DB.values().size() + 1);
        user.setUri("/user-management/" + user.getId());
        DB.put(user.getId(), user);
        return Response.status(201).contentLocation(new URI(user.getUri())).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getUserById(@PathParam("id") int id) throws URISyntaxException {
        User user = DB.get(id);
        if (user == null) {
            return Response.status(404).build();
        }
        return Response
                .status(200)
                .entity(user)
                .contentLocation(new URI("/user-management/" + id)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateUser(@PathParam("id") int id, User user) throws URISyntaxException {
        User temp = DB.get(id);
        if (user == null) {
            return Response.status(404).build();
        }
        temp.setFirstName(user.getFirstName());
        temp.setLastName(user.getLastName());
        DB.put(temp.getId(), temp);
        return Response.status(200).entity(temp).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) throws URISyntaxException {
        User user = DB.get(id);
        if (user != null) {
            DB.remove(user.getId());
            return Response.status(200).build();
        }
        return Response.status(404).build();
    }

    static {
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("John");
        user1.setLastName("Wick");
        user1.setUri("/user-management/1");

        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("Harry");
        user2.setLastName("Potter");
        user2.setUri("/user-management/2");

        DB.put(user1.getId(), user1);
        DB.put(user2.getId(), user2);
    }
}
