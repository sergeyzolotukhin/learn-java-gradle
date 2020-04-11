package ua.in.sz.pattern.gof.visitor.impl;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Builder
@RequiredArgsConstructor
public class Composite implements Component {
    private final List<Component> children = new ArrayList<>();

    private final String name;

    public void add(Component component) {
        children.add(component);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);

        visitor.preVisitChildren(this);
        children.forEach(child -> child.accept(visitor));
        visitor.postVisitChildren(this);
    }
}
