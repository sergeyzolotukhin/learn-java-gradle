package ua.in.sz.pattern.interpret.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.pattern.interpret.api.Entity;

import java.time.LocalDate;

@Slf4j
@Getter
@RequiredArgsConstructor
public class EntityVO implements Entity {
	private final LocalDate from;
	private final LocalDate to;
}
