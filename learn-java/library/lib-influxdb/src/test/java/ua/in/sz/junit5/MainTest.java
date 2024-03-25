package ua.in.sz.junit5;

import com.influxdb.client.InfluxDBClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.OutputFrame;
import org.testcontainers.containers.output.ToStringConsumer;
import org.testcontainers.images.PullPolicy;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SuppressWarnings("rawtypes")
@Slf4j
@Testcontainers
class MainTest {

    @Container
    public GenericContainer application = new GenericContainer(DockerImageName.parse("influxdb:2.7.5"))
//            .withImagePullPolicy(PullPolicy.alwaysPull())
            .withEnv("DOCKER_INFLUXDB_INIT_MODE", "setup")
            .withEnv("DOCKER_INFLUXDB_INIT_USERNAME", "immerfroehlich")
            .withEnv("DOCKER_INFLUXDB_INIT_PASSWORD", "immerfroehlich")
            .withEnv("DOCKER_INFLUXDB_INIT_ORG", "TestOrg")
            .withEnv("DOCKER_INFLUXDB_INIT_BUCKET", "myFirstBucket")
            .withEnv("DOCKER_INFLUXDB_INIT_ADMIN_TOKEN", "5dskc69ENfbtnF_g99CZgI4yDxTrZK45WbDSeY1kuz82uJFclYeEmnHyJxYRUcXVVB4O48rkn9e6p7g525wevQ==")
            .withExposedPorts(8086);

    @Test
    @SneakyThrows
    void main_1() {
        ToStringConsumer toStringConsumer = new ToStringConsumer();
        application.followOutput(toStringConsumer, OutputFrame.OutputType.STDOUT);

        log.info("{}",  toStringConsumer.toUtf8String());

        String token = "5dskc69ENfbtnF_g99CZgI4yDxTrZK45WbDSeY1kuz82uJFclYeEmnHyJxYRUcXVVB4O48rkn9e6p7g525wevQ==";
        String username = "immerfroehlich";
        String password = "immerfroehlich";
        String bucket = "myFirstBucket";
        String org = "TestOrg";
        String url = "http://" + application.getHost() + ":" + application.getFirstMappedPort();

        InfluxDBConnectionClass inConn = new InfluxDBConnectionClass();
        InfluxDBClient influxDBClient = inConn.buildConnection(url, token, bucket, org);
//        InfluxDBClient influxDBClient = inConn.buildConnection(url, token, username, password, org);

        boolean resultSingle = inConn.singlePointWrite(influxDBClient);
        if (resultSingle) {
            log.info("Single Point write done!!");
        }

        // Multiple Points
        boolean resultMultiple = inConn.writeMultiplePoints(influxDBClient);
        if (resultMultiple) {
            log.info("Write multiple points done!!");
        }

        // Write using POJO

        boolean resultPOJO = inConn.writePointbyPOJO(influxDBClient);
        if (resultPOJO) {
            log.info("Writey POJO done");
        }

        inConn.queryData(influxDBClient);

        boolean resultDelete = false;
        inConn.deleteRecord(influxDBClient);

        if (resultDelete) {
            log.info("Delete Record done!");
        }

        influxDBClient.close();
    }

}