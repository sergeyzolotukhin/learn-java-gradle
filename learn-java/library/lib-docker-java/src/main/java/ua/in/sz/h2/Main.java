package ua.in.sz.h2;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DockerClientBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
        log.info("Hello world!");

        DockerClient dockerClient
                = DockerClientBuilder.getInstance("tcp://192.168.56.2:2375")
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