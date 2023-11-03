package ua.in.sz.cxf.server;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("hello")
public class SimpleRepository {
	@GET
	public String sayHi() {
		log.info("sayHi called");

//		return "Hello Serhij" ;
		throw new IllegalStateException("111");
	}
}
