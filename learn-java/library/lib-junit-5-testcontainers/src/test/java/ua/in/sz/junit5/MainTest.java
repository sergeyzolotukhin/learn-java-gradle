package ua.in.sz.junit5;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.concurrent.TimeUnit;

@Slf4j
@Testcontainers
class MainTest {

    @Container
    public GenericContainer application = new GenericContainer(DockerImageName.parse("szolotukhin/spring-web-rest:0.0.1-SNAPSHOT"))
            .withExposedPorts(8080);

    @Test
    @SneakyThrows
    void main_1() {
        log.info("main 1 {}", this);
        TimeUnit.SECONDS.sleep(60);
        log.info("main 1 {}", this);
    }

}