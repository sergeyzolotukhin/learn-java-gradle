package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@Slf4j
public class ReadMp4FrameMain {
    public static void main(String[] args) throws Exception {

        log.info("Starting Git");

        String filePath = "j:\\_capute\\qr-data\\2024100519372950.mp4";

        ;

        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(filePath);
             Java2DFrameConverter bimConverter = new Java2DFrameConverter()
        ) {
//            grabber.setVideoFrameNumber(1);
            grabber.start();

            int i = 0;
            Frame frame;
            while ((frame = grabber.grabFrame()) != null) {
                if (i % 100 == 0) {
                    BufferedImage bi = bimConverter.convert(frame);
                    if (bi != null) {
                        String path = String.format("j:\\_capute\\qr-data\\out-frame-%08d.jpg", i);
                        ImageIO.write(bi, "png", new File(path));
                    }
                }

                i++;
            }

            log.info("Frame count: {}", i);

            grabber.stop();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}