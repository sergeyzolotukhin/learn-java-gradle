package ua.in.sz.swing;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Quiz01App {
    private static final int MAX_LENGTH = 1000;

    private static final int MIN = 0;
    private static final int MAX = 1000;

    private static final int numbers[] = new int[MAX_LENGTH];

    public static void main(String[] args) {
        for (int i = 0; i < MAX_LENGTH; i++) {
            int val = MIN + (int)(Math.random() * (MAX - MIN + 1));
            numbers[i] = val;
        }

        int result = smallestPositiveInteger(numbers);

        int[] ordered = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(ordered);

        log.info("numbers: {}", numbers);
        log.info("ordered: {}", ordered);
        log.info("result: {}", result);
    }

    private static int smallestPositiveInteger(int numbers[]) {
        for (int expected = 1; expected <= numbers.length; expected++) {

            boolean exist = false;
            for (int i = 0; i < numbers.length; i++) {
                if (expected == numbers[i]) {
                    exist = true;
                    break;
                }
            }

            if (exist) {
                continue;
            }

            return expected;
        }

        return 1;
    }
}
