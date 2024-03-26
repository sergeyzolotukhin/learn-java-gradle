package ua.in.sz.junit5;

import com.influxdb.client.InfluxDBClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

@Slf4j
@Testcontainers
class MainInExistsEnvTest {

    @Test
    @SneakyThrows
    void main_1() {
        String token = "5dskc69ENfbtnF_g99CZgI4yDxTrZK45WbDSeY1kuz82uJFclYeEmnHyJxYRUcXVVB4O48rkn9e6p7g525wevQ==";
        String bucket = "myFirstBucket";
        String org = "TestOrg";
        String host = "192.168.56.2";
        Integer port = 8086;
        String url = "http://" + host + ":" + port;

        InfluxDBConnectionClass inConn = new InfluxDBConnectionClass();
        InfluxDBClient influxDBClient = inConn.buildConnection(url, token, bucket, org);

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