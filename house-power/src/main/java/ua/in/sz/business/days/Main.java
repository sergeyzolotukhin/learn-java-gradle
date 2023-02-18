package ua.in.sz.business.days;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class Main {
    public static void main(String[] args) {
        LocalDateTime date = LocalDateTime.now();
        int second = date.getSecond();
        long bit = (1L << second) - 1L;

        String str = Long.toHexString(bit);
        log.info("[{}] - {}", str, second);
    }
}

// [11111111111111111111111111111111]
//  [1111111111111111111111111111111]
// 8 + 8 + 8 + 8 + 7
// [7f ff ff ff ff] - 39
