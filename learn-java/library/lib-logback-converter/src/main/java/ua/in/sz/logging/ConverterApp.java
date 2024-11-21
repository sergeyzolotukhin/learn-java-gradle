package ua.in.sz.logging;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.logging.logs.Task;

@Slf4j
public class ConverterApp {
    public static void main(String[] args) {
        Task task = Task.builder().name("task").description("task description").number(510).build();
        log.info("Task: {}", task);
    }
}
