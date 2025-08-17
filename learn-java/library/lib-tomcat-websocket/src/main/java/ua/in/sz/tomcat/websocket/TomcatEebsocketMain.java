package ua.in.sz.tomcat.websocket;


import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.File;

@Slf4j
public class TomcatEebsocketMain {
    public static void main(String[] args) throws Exception {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(".tomcat");
        tomcat.setPort(8081);
        tomcat.getConnector();
        String contextPath = "./learn-java/library/lib-tomcat-websocket/src/main/webapp/";
        String docBase = new File(contextPath).getAbsolutePath();

        tomcat.addWebapp("/", docBase);

        tomcat.start();
        tomcat.getServer().await();
    }
}