package ua.in.sz.h2;

import com.github.sarxos.webcam.Webcam;
import lombok.extern.slf4j.Slf4j;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.List;

@Slf4j
public class MainPatch {
    public static void main(String[] args) throws Exception {
        log.info("Starting Git");

        List<Webcam> webcams = Webcam.getWebcams();
        for (Webcam webcam : webcams) {
            log.info(webcam.getName());
        }

        Webcam webcam = Webcam.getWebcamByName("A4tech FHD 1080P PC Camera 1");
        webcam.setCustomViewSizes(new Dimension(1920, 1080));
        webcam.setViewSize(new Dimension(1920, 1080));
        webcam.open();
        ImageIO.write(webcam.getImage(), "PNG", new File("hello-world.png"));

        log.info("Ending Git");
    }
}