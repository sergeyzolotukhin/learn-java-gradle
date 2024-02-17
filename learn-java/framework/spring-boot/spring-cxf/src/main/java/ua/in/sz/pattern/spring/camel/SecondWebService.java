package ua.in.sz.pattern.spring.camel;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

@jakarta.jws.WebService
public interface SecondWebService {
    @WebMethod
    String sayHi(@WebParam(name = "text", mode = WebParam.Mode.IN) String text);
}
