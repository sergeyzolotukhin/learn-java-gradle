package ua.in.sz.mvn.invok;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;

import java.io.File;
import java.util.Collections;

@Slf4j
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        File mavenHome = new File("C:/soft/apache-maven-3.8.6");
        File projectHome = new File("D:/projects-java/simple-maven-project");

        InvocationRequest request = new DefaultInvocationRequest();
        request.setMavenHome(mavenHome);
        request.setBaseDirectory(projectHome);
        request.setPomFile(new File(projectHome,"pom.xml"));
        request.setGoals(Collections.singletonList("clean install"));

        Invoker invoker = new DefaultInvoker();
        invoker.execute(request);

        log.info("Hello world!");
    }
}