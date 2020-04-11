package ua.in.sz.pattern.gof.composite.impl;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Builder
@RequiredArgsConstructor
public class Composite implements Component {
    private static final List<Component> children = new ArrayList<>();

    private final String name;

    @Override
    public void draw() {
        log.info("Name: [{}], children: [{}]", getName(), childrenNames());
    }

    public void add(Component component) {
        children.add(component);
    }

    public void remove(Component component) {
        children.add(component);
    }

    private String childrenNames() {
        return children.stream().map(Component::getName).collect(Collectors.joining(","));
    }
}
