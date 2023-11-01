package ua.in.sz.undertow;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.util.Headers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
        HttpHandler httpHandler = exchange -> {
            log.info("get via http");
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
            exchange.getResponseSender().send("HTTP -> OK");
        };

        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(httpHandler).build();
        server.start();
    }
}