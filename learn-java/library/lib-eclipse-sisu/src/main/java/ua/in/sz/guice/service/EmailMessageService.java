package ua.in.sz.guice.service;

//import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Singleton
public class EmailMessageService implements MessageService {
    public void sendMessage(String msg, String receipient) {
        log.info("Email Message sent to {} with message={}", receipient, msg);
    }
}
