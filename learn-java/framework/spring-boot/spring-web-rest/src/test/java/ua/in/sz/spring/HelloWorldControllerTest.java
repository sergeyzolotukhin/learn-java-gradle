package ua.in.sz.spring;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class HelloWorldControllerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @SneakyThrows
    void findByPublished() {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()){
            HttpUriRequest request = RequestBuilder.get()
                    .setUri("http://localhost:8080/hello")
                    .setHeader(HttpHeaders.ACCEPT, "application/json")
                    .addHeader(HttpHeaders.AUTHORIZATION, "Basic " +  Base64.getEncoder().encodeToString(("admin:admin").getBytes()))
                    .build();

            HttpResponse httpResponse = client.execute(request);
            assertEquals(200, httpResponse.getStatusLine().getStatusCode());

            String result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            assertEquals("[\"Hello\",\"Serhij\",\"Zolotukhin\"]", result);
        }
    }

    @Test
    @SneakyThrows
    void findByPublishedHttpGet() {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()){
            HttpGet request = new HttpGet("http://localhost:8080/hello");
            request.addHeader(HttpHeaders.ACCEPT, "application/json");
            request.addHeader(HttpHeaders.AUTHORIZATION, "Basic " +  Base64.getEncoder().encodeToString(("admin:admin").getBytes()));

            HttpResponse httpResponse = client.execute(request);
            assertEquals(200, httpResponse.getStatusLine().getStatusCode());

            String result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            assertEquals("[\"Hello\",\"Serhij\",\"Zolotukhin\"]", result);
        }
    }

    @Test
    @SneakyThrows
    void findByPublishedAccessDenied() {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()){
            HttpUriRequest request = RequestBuilder.get()
                    .setUri("http://localhost:8080/hello")
                    .setHeader(HttpHeaders.ACCEPT, "application/json")
                    .build();

            HttpResponse httpResponse = client.execute(request);
            assertEquals(401, httpResponse.getStatusLine().getStatusCode());

            String result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            assertEquals("", result);
        }
    }

    @Test
    @SneakyThrows
    void findByPublishedSession() {
//        CookieStore cookieStore = new BasicCookieStore();
        try (CloseableHttpClient client = HttpClientBuilder.create()
//                .setDefaultCookieStore(cookieStore)
                .build()){
            HttpUriRequest request1 = RequestBuilder.get()
                    .setUri("http://localhost:8080/hello")
                    .setHeader(HttpHeaders.ACCEPT, "application/json")
                    .addHeader(HttpHeaders.AUTHORIZATION, "Basic " +  Base64.getEncoder().encodeToString(("admin:admin").getBytes()))
                    .build();

            HttpResponse httpResponse1 = client.execute(request1);
            assertEquals(200, httpResponse1.getStatusLine().getStatusCode());

//            cookieStore.getCookies().stream().forEach(c -> log.info("Cooke: {}", c));

//            Header header = httpResponse1.getFirstHeader("Set-Cookie");
//            log.info("Header: {}", header);

//            Arrays.asList(httpResponse1.getAllHeaders()).stream()
//                    .forEach(h -> log.info("{}", h));


            HttpUriRequest request2 = RequestBuilder.get()
                    .setUri("http://localhost:8080/hello")
                    .setHeader(HttpHeaders.ACCEPT, "application/json")
//                    .addHeader("JSESSIONID", header.getValue())
                    .addHeader(HttpHeaders.AUTHORIZATION, "Basic " +  Base64.getEncoder().encodeToString(("admin:admin").getBytes()))
                    .build();
            HttpResponse httpResponse2 = client.execute(request2);
            assertEquals(200, httpResponse2.getStatusLine().getStatusCode());

            String result = EntityUtils.toString(httpResponse2.getEntity(), "UTF-8");
            assertEquals("[\"Hello\",\"Serhij\",\"Zolotukhin\"]", result);
        }
    }
}