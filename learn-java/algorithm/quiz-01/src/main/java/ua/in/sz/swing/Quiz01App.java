package ua.in.sz.swing;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Quiz01App {
    private static final int MAX_LENGTH = 1000;

    private static final int MIN = 0;
    private static final int MAX = 1000;

    private static final int numbers[] = new int[MAX_LENGTH];

//    private static int complexity;

    public static void main(String[] args) {
        for (int i = 0; i < MAX_LENGTH; i++) {
            int val = MIN + (int) (Math.random() * (MAX - MIN + 1));
            numbers[i] = val;
        }

        int result = smallestPositiveInteger(numbers);

        int[] ordered = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(ordered);

        log.info("numbers: {}", numbers);
        log.info("ordered: {}", ordered);
        log.info("result: {}", result);
//        log.info("complexity: {}", complexity);
    }

    // Complexity - Average-Case Time - ?
    private static int smallestPositiveInteger(int numbers[]) {
        for (int expected = 1; expected <= numbers.length; expected++) {
            if (!linearSearch(numbers, expected)) {
                return expected;
            }
        }

        return 1;
    }

    // Complexity - Average-Case Time - O(n)
    private static boolean linearSearch(int[] numbers, int expected) {
        for (int i = 0; i < numbers.length; i++) {
//            complexity++;
            if (expected == numbers[i]) {
                return true;
            }
        }

        return false;
    }
}
