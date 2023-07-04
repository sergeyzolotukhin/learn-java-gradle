package ua.in.sz.jersey;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ApplicationTests {
    @Test
    public void getUser() throws IOException {
        HttpUriRequest request = new HttpGet( "http://localhost:8080/users/1");

        try (CloseableHttpClient build = HttpClientBuilder.create().build()){
            HttpResponse httpResponse = build.execute( request );

            String text = new String(httpResponse.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
            System.out.printf("Response: %s%n", text);
        }
    }
}
