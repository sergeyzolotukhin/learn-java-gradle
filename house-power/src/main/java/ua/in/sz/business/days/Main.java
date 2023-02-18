package ua.in.sz.business.days;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class Main {
    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusMonths(1);
        int day = start.getDayOfMonth();
        long bit = (1L << day) - 1L;

        String str = Long.toHexString(bit);
        int count = Long.bitCount(bit);
        log.info("[{}] - {} = {}", str, day, count);
    }
}

// [11111111111111111111111111111111]
//  [1111111111111111111111111111111]
// 8 + 8 + 8 + 8 + 7
// [7f ff ff ff ff] - 39
