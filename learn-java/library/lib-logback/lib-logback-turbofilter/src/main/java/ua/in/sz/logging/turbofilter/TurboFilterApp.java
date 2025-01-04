package ua.in.sz.logging.turbofilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TurboFilterApp {
    public static void main(String[] args) {
        log.info("Hello World {} !", "Serhij");
        log.info("How are you, {} !", "Serhij");
        log.info("Thank you. I am fine {} !", "Serhij");
    }
}
