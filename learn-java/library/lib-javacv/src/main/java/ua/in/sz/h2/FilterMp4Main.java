package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameFilter;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;

@Slf4j
public class FilterMp4Main {
    public static void main(String[] args) throws Exception {

        log.info("Starting Git");

        String filePath = "j:\\_capute\\qr-data\\2024100519372950.mp4";

        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(filePath)) {
            grabber.start();

            String filters = "fps=fps=1";
            FFmpegFrameFilter filter = new FFmpegFrameFilter(filters,
                    grabber.getImageWidth(),
                    grabber.getImageHeight());

            filter.setPixelFormat(grabber.getPixelFormat());
            filter.setAspectRatio(grabber.getAspectRatio());
            filter.setFrameRate(grabber.getFrameRate());
            filter.start();

            int i = 0, f = 0;
            Frame frame;
            while ((frame = grabber.grabFrame()) != null) {
                filter.push(frame, grabber.getPixelFormat());
                Frame filteredFrame = filter.pull();
                if (filteredFrame != null) {
                    f++;
                }

                i++;
            }

            log.info("Frame count: {} -> {}", i, f);

            filter.stop();
            grabber.stop();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}