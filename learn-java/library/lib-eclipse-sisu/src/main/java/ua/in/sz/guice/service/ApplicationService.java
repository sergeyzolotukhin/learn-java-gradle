package ua.in.sz.guice.service;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Named;

@Slf4j
@Named("application")
public class ApplicationService {

    private final MessageService service;

    @Inject
    public ApplicationService(@Named("noop") MessageService service) {
        this.service = service;
    }

    public void sendMessage(String msg, String rec) {
        service.sendMessage(msg, rec);
    }
}
