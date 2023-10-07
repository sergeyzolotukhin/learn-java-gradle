package ua.in.sz.guice.service;

import jakarta.inject.Inject;
import jakarta.inject.Named;

public class ApplicationService {

    private final MessageService service;

    @Inject
    public ApplicationService(@Named("email") MessageService service) {
        this.service = service;
    }

    public void sendMessage(String msg, String rec) {
        service.sendMessage(msg, rec);
    }
}
