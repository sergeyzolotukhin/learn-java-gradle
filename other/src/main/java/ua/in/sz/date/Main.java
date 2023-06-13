package ua.in.sz.date;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Date d = new Date();
        log.info("{}", d);

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        log.info("{}", c.getTime());
    }
}
