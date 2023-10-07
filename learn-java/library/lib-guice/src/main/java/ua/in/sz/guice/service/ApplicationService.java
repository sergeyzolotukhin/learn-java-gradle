package ua.in.sz.guice.service;

//import javax.inject.Inject;

import com.google.inject.Inject;

public class ApplicationService {

    private final MessageService service;

    @Inject
    public ApplicationService(MessageService service) {
        this.service = service;
    }

    public void sendMessage(String msg, String rec) {
        service.sendMessage(msg, rec);
    }
}
