package ua.in.sz.interview;

import lombok.extern.slf4j.Slf4j;

/**
 * -XX:AutoBoxCacheMax=128
 */
@Slf4j
public class IntCache {
    public static void main(String[] args) {
        Integer a=0;
        Integer b=0;
        while (a==b) {
            ++a;
            ++b;
        }
        log.info("The output is:" + a);
    }
}
