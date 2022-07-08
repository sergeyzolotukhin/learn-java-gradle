package ua.in.sz.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {

    @Test
    @DisplayName("Size should be one after added item")
    void add() {
        MyQueue queue = new MyQueue();
        queue.add();

        Assertions.assertEquals(1, queue.size());
    }

    @Test
    @DisplayName("Size should be zero after remove last item")
    void remove() {
        MyQueue queue = new MyQueue();
        queue.add();
        queue.remove();

        Assertions.assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("Size should be zero in new queue")
    void size() {
        MyQueue queue = new MyQueue();

        Assertions.assertEquals(0, queue.size());
    }
}