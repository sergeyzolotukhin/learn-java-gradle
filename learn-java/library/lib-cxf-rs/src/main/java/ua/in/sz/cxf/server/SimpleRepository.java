package ua.in.sz.cxf.server;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

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
