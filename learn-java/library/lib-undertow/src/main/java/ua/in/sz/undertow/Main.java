package ua.in.sz.undertow;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.attribute.ExchangeAttributes;
import io.undertow.server.HttpHandler;
import io.undertow.util.Headers;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.security.KeyStore;

import static io.undertow.Handlers.predicate;
import static io.undertow.predicate.Predicates.secure;

@Slf4j
public class Main {
    private static final char[] STORE_PASSWORD = "password".toCharArray();

    public static void main(String[] args) throws Exception {
        SSLContext sslContext = createSSLContext(
                loadKeyStore("/server.keystore.jks"),
                loadKeyStore("/server.truststore.jks"));


        HttpHandler httpsHandler = exchange -> {
            log.info("get via https");
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
            exchange.getResponseSender().send("HTTP with SSL -> OK");
        };

        HttpHandler httpHandler = exchange -> {
            log.info("get via http");
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
            exchange.getResponseSender().send("HTTP -> OK");
        };

        HttpHandler handler = Handlers.header(
                predicate(
                        secure(),
                        httpsHandler,
                        httpHandler),
                "x-undertow-transport", ExchangeAttributes.transportProtocol()
        );

        Undertow server = Undertow.builder()
                .addHttpsListener(8443, "localhost", sslContext)
//                .addHttpListener(8080, "localhost")
                .setHandler(handler)
                .setHandler(httpsHandler).build();
        server.start();
    }

    private static KeyStore loadKeyStore(String name) throws Exception {
        InputStream stream = Main.class.getResourceAsStream(name);

        if (stream == null) {
            throw new RuntimeException("Could not load keystore");
        }
        try (InputStream is = stream) {
            KeyStore loadedKeystore = KeyStore.getInstance("JKS");
            loadedKeystore.load(is, STORE_PASSWORD);
            return loadedKeystore;
        }
    }

    private static SSLContext createSSLContext(KeyStore keyStore, KeyStore trustStore) throws Exception {
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, STORE_PASSWORD);
        KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(trustStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagers, trustManagers, null);

        return sslContext;
    }
}