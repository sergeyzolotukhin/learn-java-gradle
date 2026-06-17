package ua.in.sz.swing;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
public class Quiz01App {
    private static final int MAX_LENGTH = 100;

    private static final int MIN = 0;
    private static final int MAX = 100;

    private static final int numbers[] = new int[MAX_LENGTH];

    public static void main(String[] args) {
        for (int i = 0; i < MAX_LENGTH; i++) {
            int val = MIN + (int)(Math.random() * (MAX - MIN + 1));
            numbers[i] = val;
        }

        Arrays.sort(numbers);

        log.info("values: {}", numbers);
    }
}
