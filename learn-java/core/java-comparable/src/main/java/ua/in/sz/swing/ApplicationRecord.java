package ua.in.sz.swing;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


@Slf4j
public class ApplicationRecord {

    public static void main(String[] args) {
        Glass plastic = new Glass(Glass.Size.BIG,"Plastic");
        Glass glass = new Glass(Glass.Size.BIG,"glass");

        // hash set
        Set<Glass> set = new HashSet<>();
        set.add(plastic);
        set.add(glass);

        log.info("HashSet: {}", set);

        // tree set
        Set<Glass> treeSet = new TreeSet<>();
        treeSet.add(plastic);
        treeSet.add(glass);

        log.info("TreeSet: {}", treeSet);
    }
}
