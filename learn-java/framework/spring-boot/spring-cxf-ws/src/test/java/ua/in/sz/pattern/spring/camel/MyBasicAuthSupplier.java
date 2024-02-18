package ua.in.sz.pattern.spring.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.common.util.Base64Utility;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.http.auth.HttpAuthSupplier;

import java.net.URI;

@Slf4j
public class MyBasicAuthSupplier implements HttpAuthSupplier {
    private static final String ENCODE_BASIC_AUTH_WITH_ISO8859 = "encode.basicauth.with.iso8859";

    public boolean requiresRequestCaching() {
        return false;
    }

    @Override
    public String getAuthorization(AuthorizationPolicy authPolicy, URI currentURI, Message message, String fullHeader) {
        log.info("AUTH MY");

        return getBasicAuthHeader("admin", "admin");
    }

    public static String getBasicAuthHeader(String userName, String passwd) {
        final String userAndPass = userName + ':' + passwd;
        return "Basic " + Base64Utility.encode(userAndPass.getBytes());
    }
}
