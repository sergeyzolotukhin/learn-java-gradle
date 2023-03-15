package ua.in.sz.logging;

public class MyException extends Exception {

    public MyException(String text) {
        super(text);
        setStackTrace(new StackTraceElement[0]);
    }
}
