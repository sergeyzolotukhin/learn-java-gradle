package ua.in.sz.pattern.spring.integration;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(name = "myGateway", defaultRequestChannel = "fileChannel")
public interface ReturningGateway {
    void placeOrder(String order);
}
