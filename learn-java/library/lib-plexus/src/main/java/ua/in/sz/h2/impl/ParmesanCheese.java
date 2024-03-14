package ua.in.sz.h2.impl;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.h2.Cheese;
import ua.in.sz.h2.Second;

@Slf4j
public class ParmesanCheese implements Cheese {
    private Second second;
    private Second other;

    @Override
    public void print() {
        log.info("Hello Serhij 1111");

        second.print();

        other.print();
    }
}
