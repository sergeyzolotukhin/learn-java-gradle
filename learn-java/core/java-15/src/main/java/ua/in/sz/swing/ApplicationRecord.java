package ua.in.sz.swing;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ApplicationRecord {

    public static void main(String[] args) {
        Person person = new Person("Serhij", 40);
        log.info("Person: {}", person);

        Double collect = Stream.of(1, 2, 3, 4)
                .collect(
                        Collectors.teeing(
                                Collectors.summingDouble(i -> i),
                                Collectors.counting(),
                                (sum, n) -> sum / n
                        ));
        log.info("Sum / n = {}", collect);
    }

    public record Person(String name, int age) {
        public Person {
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
        }
    }
}
