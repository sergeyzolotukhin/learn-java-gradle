package ua.in.sz.quque;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;
import java.util.Queue;

@Slf4j
public class App {
    public static void main(String[] args) {
        Queue<Task> queue = new PriorityQueue<>();

        queue.add(Task.of(0, () -> {
                    log.info("Order power supply unit");

                    queue.add(Task.of(1, () -> log.info("Move power supply unit")));
                }));

        queue.add(Task.of(2, () -> log.info("Take power supply unit")));
        queue.add(Task.of(3, () -> log.info("Drop PSU")));

        Task task;
        while ((task = queue.poll()) != null) {
            task.action.run();
        }
    }

    @AllArgsConstructor(staticName = "of")
    private static class Task implements Comparable<Task> {
        private Integer tick;
        private Runnable action;

        @Override
        public int compareTo(Task other) {
            return tick.compareTo(other.tick);
        }
    }
}
