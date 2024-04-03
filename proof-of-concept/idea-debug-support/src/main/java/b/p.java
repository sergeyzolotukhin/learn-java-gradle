package b;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.hibernate.multiple.sessions.entities.Attribute;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class p {
    public static boolean one(Object o) {
        log.info("Hello Serhij from debug");
        return false;
    }

    public static boolean two(Object o) {
        log.info("This is a second message");
        log.info("This is a second message {}", o);
        return false;
    }

    public static boolean tree(Object o) {
        log.info("Three {}", o);
        return false;
    }

    public static boolean attr(Set<Attribute> attributes) {
        log.info("Three: [{}]", attributes.stream().map(Attribute::getName).collect(Collectors.joining(", ")));
        return false;
    }
}
