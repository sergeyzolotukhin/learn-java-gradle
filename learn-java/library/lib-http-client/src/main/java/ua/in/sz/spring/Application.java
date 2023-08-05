package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Slf4j
public class Application {
    public static void main(String[] args) throws IOException {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(20);
        cm.setDefaultMaxPerRoute(20);

        try (CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build()) {
            HttpGet httpGet = new HttpGet("http://localhost:8080/hello");
            for (int i = 0; i < 200; i++) {
                // We can remove the try with a resource to emulate connection leak
                try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                    log.info("Status line: [{}]", response.getStatusLine().getStatusCode());

                    // We can comment those line to emulate connection leak
                    HttpEntity entity = response.getEntity();
                    log.info("Entity: {}", EntityUtils.toString(entity));
                }
            }
        }
    }
}
