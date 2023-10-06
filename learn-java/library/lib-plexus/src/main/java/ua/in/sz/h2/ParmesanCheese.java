package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;

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
