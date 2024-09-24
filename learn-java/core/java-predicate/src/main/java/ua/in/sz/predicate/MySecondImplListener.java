package ua.in.sz.predicate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MySecondImplListener implements MyListener {
    public void print() {
        log.info("MyImplListener second");
    }
}
