package ua.in.sz.h2;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.RemoteApiVersion;
import com.github.dockerjava.jaxrs.JerseyDockerHttpClient;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
        log.info("Hello world!");

//        DefaultDockerClientConfig dockerClientConfig = DefaultDockerClientConfig
//                .createDefaultConfigBuilder()
//                .withDockerHost("tcp://192.168.56.2:2375")
//                .withApiVersion(RemoteApiVersion.VERSION_1_25)
//                .build();

//        int dockerHttpClient = JerseyDockerHttpClient.Builder()
//                .dockerHost(dockerClientConfig.getDockerHost())
//                .sslConfig(dockerClientConfig.sslConfig)
//                .build();

        DockerClient dockerClient
                = DockerClientBuilder.getInstance("tcp://192.168.56.2:2375")
//                .withDockerHttpClient(dockerClientConfig)
                .build();

        List<Image> images = dockerClient.listImagesCmd()
                .withShowAll(true)
                .exec();

        for (Image image : images) {
            for (String tag : image.getRepoTags()) {
                log.info("image: [{}]", tag);
            }
        }

        dockerClient.close();
    }
}