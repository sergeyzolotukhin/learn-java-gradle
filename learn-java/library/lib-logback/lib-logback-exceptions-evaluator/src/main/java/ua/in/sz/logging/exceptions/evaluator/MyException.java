package ua.in.sz.logging.exceptions.evaluator;

public class MyException extends Exception {

    public MyException(String text) {
        super(text);
        setStackTrace(new StackTraceElement[0]);
    }
}
