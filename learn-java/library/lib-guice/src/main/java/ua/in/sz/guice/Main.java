package ua.in.sz.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import ua.in.sz.guice.service.ApplicationService;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());

        ApplicationService app = injector.getInstance(ApplicationService.class);
        app.sendMessage("Hi Serhij --->", "serhij@hi.com");
    }
}
