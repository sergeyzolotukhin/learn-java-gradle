package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
public class LoggingResponseInterceptor implements HttpResponseInterceptor {

    @Override
    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
        String message = buildStatusEntry(response)
                + buildHeadersEntry(response.getAllHeaders())
                + buildEntityEntry(response);
        log.info(message);
    }

    private String buildStatusEntry(HttpResponse response) {
        return "\nResponse - "
                + response.getStatusLine().getStatusCode() + " "
                + response.getStatusLine().getReasonPhrase();
    }

    private String buildHeadersEntry(Header[] headers) {
        return "\nHeaders: [\n"
                + Arrays.stream(headers)
                .map(header -> header.getName() + ": " + header.getValue())
                .collect(Collectors.joining(",\n "))
                + "\n]\n";
    }

    private String buildEntityEntry(HttpResponse response) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String payload = buffer.lines().collect(Collectors.joining("\n"));
        response.setEntity(new StringEntity(payload));
        return "\nPayload: \n" + payload;
    }

}
