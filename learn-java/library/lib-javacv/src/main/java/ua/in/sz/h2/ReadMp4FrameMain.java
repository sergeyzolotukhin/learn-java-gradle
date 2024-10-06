package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@Slf4j
public class ReadMp4FrameMain {
    public static void main(String[] args) throws Exception {
        log.info("start");

        avutil.av_log_set_level(avutil.AV_LOG_QUIET);

        String filePath = "j:\\_capute\\qr-data\\2024100519372950.mp4";

        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(filePath)) {
            grabber.start();

            int i=0;
            Frame frame;
            while ((frame = grabber.grabFrame()) != null) {
                i++;
                log.info("Frame number: {} -> {}", grabber.getFrameNumber(), frame.pictType);
            }

            log.info("Frame count: {}", i);

            grabber.stop();
            grabber.release();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        log.info("end");
    }
}