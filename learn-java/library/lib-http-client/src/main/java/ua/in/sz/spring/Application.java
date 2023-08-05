package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Slf4j
public class Application {
    public static void main(String[] args) throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://localhost:8080/hello");
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                log.info("Status line: [{}]", response.getStatusLine());

                HttpEntity entity = response.getEntity();
                log.info("Entity: [{}]", EntityUtils.toString(entity));
            }
        }
    }
}
