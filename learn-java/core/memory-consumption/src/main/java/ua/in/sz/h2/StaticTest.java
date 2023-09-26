package ua.in.sz.h2;

import java.util.ArrayList;
import java.util.List;

public class StaticTest {
    // remove `static` to make it garbage-collect-able
    public static List<Double> list = new ArrayList<>();

    public void populateList() {
        for (int i = 0; i < 100_000; i++) {
            list.add((double) i);
        }
    }

    public static void main() {
        new StaticTest().populateList();
    }
}
