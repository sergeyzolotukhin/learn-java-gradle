package ua.in.sz.pattern.gof.visitor.impl;

public interface Visitor {
    void visit(Component component);

    default void preVisitChildren(Component component) {

    }

    default void postVisitChildren(Component component) {

    }
}
