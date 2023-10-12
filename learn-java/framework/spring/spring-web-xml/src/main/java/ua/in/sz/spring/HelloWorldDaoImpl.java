package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWorldDaoImpl implements HelloWorldDao {

    @Override
    public void doTask() {
        try {
            log.info("Start task");

            long count = 0;
            for (int i = 0; i < 1; i++) {
                count = countEmployee();
            }

            log.info(String.format("Employee count: %d", count));

            log.info("End task");
        } catch (Exception e) {
            log.error("Can't do task", e);
        }

    }

    private long countEmployee() {
        return 1L;
    }
}
