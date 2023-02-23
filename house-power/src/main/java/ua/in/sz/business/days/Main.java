package ua.in.sz.business.days;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

@Slf4j
public class Main {
    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusMonths(1);
        int day = start.getDayOfMonth();
        long bit = (1L << day) - 1L;

        String str = StringUtils.leftPad(Long.toBinaryString(bit), 32, "0");

        char[] chars = str.toCharArray();
        char[] result = new char[chars.length + chars.length / 4];
        int j = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i % 8 == 0 && i != 0) result[j++] = ' ';
            result[j++] = chars[i];
        }
        String s = new String(result, 0, j);

        int count = Long.bitCount(bit);
        log.info("[{}] - {} = {}", s, day, count);
    }
}

// [11111111111111111111111111111111]
//  [1111111111111111111111111111111]
// 8 + 8 + 8 + 8 + 7
// [7f ff ff ff ff] - 39
