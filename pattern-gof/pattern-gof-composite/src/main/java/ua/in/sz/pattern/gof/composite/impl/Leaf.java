package ua.in.sz.pattern.gof.composite.impl;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@RequiredArgsConstructor
public class Leaf implements Component {
    private final String name;

    @Override
    public void draw() {
        log.info("Name: {}", getName());
    }
}
