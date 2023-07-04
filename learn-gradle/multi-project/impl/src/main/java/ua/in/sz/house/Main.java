package ua.in.sz.house;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Interface obj = new Implementation();
        obj.print();

        ArrayList<String> strings = Lists.newArrayList("1", "2");
    }
}
