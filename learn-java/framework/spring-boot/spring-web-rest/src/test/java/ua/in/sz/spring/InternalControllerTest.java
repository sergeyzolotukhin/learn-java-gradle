package ua.in.sz.spring;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class InternalControllerTest {

    @Test
    @SneakyThrows
    void findByPublished() {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()){
            HttpUriRequest request = RequestBuilder.get()
                    .setUri("http://localhost:8080/api/external/hello")
                    .setHeader(HttpHeaders.ACCEPT, "application/json")
                    .addHeader(HttpHeaders.AUTHORIZATION, "Basic " +  Base64.getEncoder().encodeToString(("admin:admin").getBytes()))
                    .build();

            HttpResponse httpResponse = client.execute(request);
            assertEquals(200, httpResponse.getStatusLine().getStatusCode());

            String result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            assertEquals("[\"Hello external\",\"Serhij\",\"Zolotukhin\"]", result);
        }
    }

    @Test
    @SneakyThrows
    void findByPublishedHttpGet() {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()){
            HttpGet request = new HttpGet("http://localhost:8080/api/external/hello");
            request.addHeader(HttpHeaders.ACCEPT, "application/json");
            request.addHeader(HttpHeaders.AUTHORIZATION, "Basic " +  Base64.getEncoder().encodeToString(("admin:admin").getBytes()));

            HttpResponse httpResponse = client.execute(request);
            assertEquals(200, httpResponse.getStatusLine().getStatusCode());

            String result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            assertEquals("[\"Hello external\",\"Serhij\",\"Zolotukhin\"]", result);
        }
    }

    @Test
    @SneakyThrows
    void findByPublishedHttpGet_CredentialsProvider() {
        BasicCredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("admin", "admin"));

        try (CloseableHttpClient client = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .build()
        ){
            HttpGet request = new HttpGet("http://localhost:8080/api/external/hello");
            request.addHeader(HttpHeaders.ACCEPT, "application/json");

            HttpContext context = new BasicHttpContext();
            HttpResponse httpResponse = client.execute(request, context);
            assertEquals(200, httpResponse.getStatusLine().getStatusCode());

            String result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            assertEquals("[\"Hello external\",\"Serhij\",\"Zolotukhin\"]", result);
        }
    }

    @Test
    @SneakyThrows
    void findByPublishedAccessDenied() {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()){
            HttpUriRequest request = RequestBuilder.get()
                    .setUri("http://localhost:8080/api/external/hello")
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
        try (CloseableHttpClient client = HttpClientBuilder.create().build()){
            HttpUriRequest request1 = RequestBuilder.get()
                    .setUri("http://localhost:8080/api/external/hello")
                    .setHeader(HttpHeaders.ACCEPT, "application/json")
                    .addHeader(HttpHeaders.AUTHORIZATION, "Basic " +  Base64.getEncoder().encodeToString(("admin:admin").getBytes()))
                    .build();

            HttpResponse httpResponse1 = client.execute(request1);
            assertEquals(200, httpResponse1.getStatusLine().getStatusCode());


            HttpUriRequest request2 = RequestBuilder.get()
                    .setUri("http://localhost:8080/api/external/hello")
                    .setHeader(HttpHeaders.ACCEPT, "application/json")
                    .build();
            HttpResponse httpResponse2 = client.execute(request2);
            assertEquals(200, httpResponse2.getStatusLine().getStatusCode());

            String result = EntityUtils.toString(httpResponse2.getEntity(), "UTF-8");
            assertEquals("[\"Hello external\",\"Serhij\",\"Zolotukhin\"]", result);
        }
    }
}