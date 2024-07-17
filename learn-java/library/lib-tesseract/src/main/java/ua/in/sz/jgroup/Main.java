package ua.in.sz.jgroup;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;

@Slf4j
public class Main {

    public static void main(String[] args) throws Exception {
        File imageFile = new File("D:/projects-java/_learn-java-gradle/learn-java/library/lib-tesseract/src/main/resources/source-1.png");
        ITesseract instance = new Tesseract();
        instance.setDatapath("D:/projects-java/_learn-java-gradle/learn-java/library/lib-tesseract/src/main/resources");
        instance.setLanguage("eng");

        String result = instance.doOCR(imageFile);
        log.info("Text: {}", result);
    }
}