package ua.in.sz.predicate;

import lombok.extern.slf4j.Slf4j;

import java.util.ServiceLoader;

@Slf4j
public class MainApp {
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Iterable<MyListener> extensions = ServiceLoader.load(MyListener.class, classLoader);
        extensions.forEach(MyListener::print);
    }
}
