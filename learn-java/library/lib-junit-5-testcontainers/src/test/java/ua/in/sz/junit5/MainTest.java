package ua.in.sz.junit5;

import io.restassured.RestAssured;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.PullPolicy;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@Slf4j
@Testcontainers
class MainTest {

    @Container
    public GenericContainer application = new GenericContainer(DockerImageName.parse("szolotukhin/spring-web-rest:0.0.1-SNAPSHOT"))
            .withImagePullPolicy(PullPolicy.alwaysPull())
            .withExposedPorts(8080);

    @Test
    @SneakyThrows
    void main_1() {
        List<String> messages = RestAssured.given()
                .baseUri("http://" + application.getHost() + ":" + application.getFirstMappedPort())
                .contentType("application/json")
                .when()
                .get("/api/internal/hello")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .jsonPath().getList(".", String.class);

        log.info("response: {}", messages);
    }

}