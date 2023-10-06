package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;

@Slf4j
@Singleton
public class EmailService implements MessageService {

    public boolean sendMessage(String msg, String receipient) {
        log.info("Email Message sent to {} with message={}", receipient, msg);
        return true;
    }

}
