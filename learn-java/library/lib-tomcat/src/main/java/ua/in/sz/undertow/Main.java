package ua.in.sz.undertow;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {

        String webappDirLocation = "learn-java/library/lib-tomcat/src/main/webapp/";
        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8080);

        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        log.info("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

        File additionWebInfClasses = new File("learn-java/library/lib-tomcat/build/classes");

        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(
                resources,
                "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(),
                "/"));

        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
    }
}