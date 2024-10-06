package ua.in.sz.h2;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ReadQRCodeMain {
    public static void main(String[] args) throws Exception {
        log.info("Starting Git");

//        String filePath = "D:\\projects-java\\_learn-java-gradle\\learn-java\\library\\lib-zxing\\src\\main\\resources\\qr.png";
//            String filePath = "j:\\_capute\\RECentral\\2024100513310259.png";
        String filePath = "D:\\projects-java\\_learn-java-gradle\\learn-java\\library\\lib-zxing\\JD.png";

        String charset = "UTF-8";
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hintMap.put(EncodeHintType.QR_VERSION, 40);

        log.info( "QRCode output: {}", readQR(filePath, charset, hintMap));

        log.info("Ending Git");
    }

    public static String readQR(String path, String charset, Map hashMap) throws Exception {
        BinaryBitmap binaryBitmap = new BinaryBitmap(
                new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(path)))));

        Result result = new MultiFormatReader().decode(binaryBitmap);
        return result.getText();
    }
}