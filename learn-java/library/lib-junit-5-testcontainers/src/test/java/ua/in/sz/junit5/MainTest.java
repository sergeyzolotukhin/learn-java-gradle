package ua.in.sz.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Slf4j
@Testcontainers
class MainTest {

    @Container
    public GenericContainer application = new GenericContainer(DockerImageName.parse("spring-boot-web-rest:latest"))
            .withExposedPorts(8080);

    @Test
    void main_1() {
        log.info("main 1 {}", this);
    }

}