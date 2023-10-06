package ua.in.sz.h2;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());

        MyApplication app = injector.getInstance(MyApplication.class);
        app.sendMessage("Hi Pankaj", "pankaj@abc.com");
    }
}
