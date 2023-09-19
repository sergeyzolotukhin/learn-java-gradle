package ua.in.sz.h2;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.system.SystemInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {

        JenkinsClient client = JenkinsClient.builder()
                .endPoint("http://127.0.0.1:8080")
//                .credentials("admin:password")
                .build();

        SystemInfo systemInfo = client.api().systemApi().systemInfo();

        log.info("Jenkins version: [{}]", systemInfo.jenkinsVersion());
    }
}