package ua.in.sz.encoding;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

@Slf4j
public class EncodingMain {
    public static void main(String[] args) {
        Charset charset = Charset.defaultCharset();
        log.info("The charset is [{}]", charset);
        log.info("The text is [{}]", "My text");
    }
}
