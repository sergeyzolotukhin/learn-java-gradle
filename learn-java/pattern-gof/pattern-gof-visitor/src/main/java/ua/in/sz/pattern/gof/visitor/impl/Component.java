package ua.in.sz.pattern.gof.visitor.impl;

public interface Component {
    String getName();

    void accept(Visitor visitor);
}
