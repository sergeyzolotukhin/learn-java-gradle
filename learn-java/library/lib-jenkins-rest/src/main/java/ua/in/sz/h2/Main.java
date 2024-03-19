package ua.in.sz.h2;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.common.IntegerResponse;
import com.cdancy.jenkins.rest.domain.system.SystemInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class Main {
    public static void main(String[] args) {

        JenkinsClient client = JenkinsClient.builder()
                .endPoint("http://127.0.0.1:8080")
//                .credentials("admin:password")
                .build();

        SystemInfo systemInfo = client.api().systemApi().systemInfo();

        log.info("Jenkins version: [{}]", systemInfo.jenkinsVersion());

        Map<String, List<String>> parameters = new HashMap<>();
        parameters.put("ARG_1", Collections.singletonList("SZ 1" + new Date()));
        parameters.put("ARG_2", Collections.singletonList("SZ 2" + new Date()));
        parameters.put("ARG_3", Collections.singletonList("SZ 3" + new Date()));


        IntegerResponse build = client.api().jobsApi().buildWithParameters(null, "job_4", parameters);

        log.info("Jenkins build: [{}]", build.value());
    }
}