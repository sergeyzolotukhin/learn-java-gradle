package ua.in.sz.junit;

public class MyQueue {
    private int size = 0;

    public void add() {
        size++;
    }

    public void remove() {
        size--;
    }

    public int size() {
        return size;
    }
}
