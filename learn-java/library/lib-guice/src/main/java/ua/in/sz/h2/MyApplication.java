package ua.in.sz.h2;

import javax.inject.Inject;

public class MyApplication {

    private MessageService service;

    @Inject
    public void setService(MessageService service) {
        this.service = service;
    }

    public boolean sendMessage(String msg, String rec) {
        return service.sendMessage(msg, rec);
    }
}
