package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameFilter;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.bytedeco.ffmpeg.global.avcodec.AV_CODEC_ID_H264;
import static org.bytedeco.ffmpeg.global.avutil.AV_PIX_FMT_YUV420P;

@Slf4j
public class ConvertWithFilterMp4Main {
    public static void main(String[] args) throws Exception {
        avutil.av_log_set_level(avutil.AV_LOG_QUIET);

        String filePath = "j:\\_capute\\qr-data\\2024100519372950.mp4";
        Path outputFile = Paths.get("j:\\_capute\\qr-data\\2024100519372950-output.mp4");

        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(filePath)) {
            grabber.start();

            try (FFmpegFrameFilter filter = new FFmpegFrameFilter(
                    "fps=fps=1",
                    grabber.getImageWidth(),
                    grabber.getImageHeight())) {
                filter.start();

                try (FFmpegFrameRecorder recorder =
                             new FFmpegFrameRecorder(
                                     outputFile.toFile(),
                                     filter.getImageWidth(),
                                     filter.getImageHeight(),
                                     0)) {

                    recorder.setVideoCodec(AV_CODEC_ID_H264);
                    recorder.setPixelFormat(AV_PIX_FMT_YUV420P);
                    recorder.setFormat("mp4");
                    recorder.setFrameRate(grabber.getFrameRate());
                    recorder.start();

                    Frame frame = grabber.grabImage();
                    while (frame != null) {
                        filter.push(frame, grabber.getPixelFormat());

                        Frame filteredFrame = filter.pull();
                        if (filteredFrame != null) {
                            recorder.record(filteredFrame);
                        }
                        frame = grabber.grabImage();
                    }

                    grabber.flush();

                    filter.push(null);
                    Frame filteredFrame = filter.pull();
                    recorder.record(filteredFrame);

                    recorder.stop();
                    recorder.release();
                }

                filter.stop();
                filter.release();
            }

            grabber.stop();
            grabber.release();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}