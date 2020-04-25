package ua.in.sz.pattern.interpret.expression.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.pattern.interpret.api.Context;
import ua.in.sz.pattern.interpret.api.Entity;

import java.time.LocalDate;

@Slf4j
@Getter
@RequiredArgsConstructor
public class ContextImpl implements Context {
	private final Entity entity;
	private final LocalDate currentDate;


}
