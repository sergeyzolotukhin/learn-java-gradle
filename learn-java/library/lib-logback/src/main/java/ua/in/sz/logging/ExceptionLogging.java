package ua.in.sz.logging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionLogging {
    public static void main(String[] args) {
        Exception e = new MyException("");
        log.info("Text", e);
    }
}
