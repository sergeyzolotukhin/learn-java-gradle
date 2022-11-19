package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Interface obj = new Implementation();
        obj.print();
    }
}
