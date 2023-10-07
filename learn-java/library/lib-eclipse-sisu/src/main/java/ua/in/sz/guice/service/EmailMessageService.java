package ua.in.sz.guice.service;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;

@Slf4j
@Named("email")
public class EmailMessageService implements MessageService {
    public void sendMessage(String msg, String receipient) {
        log.info("Email Message sent to {} with message={}", receipient, msg);
    }
}
