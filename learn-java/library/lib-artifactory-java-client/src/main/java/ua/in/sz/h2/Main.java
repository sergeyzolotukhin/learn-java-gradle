package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.jfrog.artifactory.client.Artifactory;
import org.jfrog.artifactory.client.ArtifactoryClientBuilder;
import org.jfrog.artifactory.client.model.RepoPath;

import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Hello world!");

        Artifactory artifactory = ArtifactoryClientBuilder.create()
                .setUrl("http://192.168.56.2:8082/artifactory")
                .setUsername("admin")
                .setPassword("Szolotukhin1")
                .build();

        List<RepoPath> results = artifactory.searches().artifactsByGavc()
                .groupId("ua.in.sz.mvn")
                .artifactId("jaxb-bean-validation")
                .version("0.0.5-SNAPSHOT")
                .doSearch();

        for (RepoPath searchItem : results) {
            String repoKey = searchItem.getRepoKey();
            String itemPath = searchItem.getItemPath();
            log.info("[{}] [{}]", repoKey, itemPath);
        }

        artifactory.close();
    }
}