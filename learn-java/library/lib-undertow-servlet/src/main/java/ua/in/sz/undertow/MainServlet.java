package ua.in.sz.undertow;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import jakarta.servlet.ServletException;

public class MainServlet {
    public static void main(String[] args) throws ServletException {
        DeploymentInfo servletBuilder = Servlets.deployment()
                .setClassLoader(MainServlet.class.getClassLoader())
                .setContextPath("/")
                .setDeploymentName("app.war")
                .addServlets(
                        Servlets.servlet("MessageServlet", MessageServlet.class)
                                .addMapping("/*")
                );

        DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
        manager.deploy();

        HttpHandler httpHandler = manager.start();

        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(httpHandler)
                .build();

        server.start();
    }
}
