package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWorldDaoImpl implements HelloWorldDao {

    @Override
    public void doTask() {
        log.info("Start task");
    }
}
