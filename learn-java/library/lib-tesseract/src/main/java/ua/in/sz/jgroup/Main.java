package ua.in.sz.jgroup;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;

@Slf4j
public class Main {

    public static void main(String[] args) throws Exception {
        File imageFile = new File("j:\\_capute\\ge\\2024100718320771.mp4_snapshot_00.50.049.png");
//        File imageFile = new File("D:/projects-java/_learn-java-gradle/learn-java/library/lib-tesseract/src/main/resources/2024100511233621.png");
        ITesseract instance = new Tesseract();
        instance.setDatapath("D:/projects-java/_learn-java-gradle/learn-java/library/lib-tesseract/src/main/resources");
        instance.setLanguage("eng");

        String result = instance.doOCR(imageFile);
        log.info("Text: {}", result);
    }
}