package ua.in.sz.guice.service;

import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

//import javax.inject.Singleton;

@Slf4j
@Singleton
public class EmailMessageService implements MessageService {
    public void sendMessage(String msg, String receipient) {
        log.info("Email Message sent to {} with message={}", receipient, msg);
    }
}
