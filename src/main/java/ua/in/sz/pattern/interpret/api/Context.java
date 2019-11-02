package ua.in.sz.pattern.interpret.api;

import java.time.LocalDate;

public interface Context {
	LocalDate getCurrentDate();
	Entity getEntity();
}
