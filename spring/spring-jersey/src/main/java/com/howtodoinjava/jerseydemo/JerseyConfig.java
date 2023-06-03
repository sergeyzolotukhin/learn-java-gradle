package com.howtodoinjava.jerseydemo;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

//import jakarta.ws.rs.ApplicationPath;

@Component
//@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(UserResource.class);
    }
}
