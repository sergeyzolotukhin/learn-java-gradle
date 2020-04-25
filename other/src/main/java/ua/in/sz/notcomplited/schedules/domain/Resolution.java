package ua.in.sz.notcomplited.schedules.domain;

import org.joda.time.Period;

public enum Resolution {
	PT15M("PT15M"),
	PT30M("PT30M"),
	PT45M("PT45M"),
	PT1H("PT1H"),
	P1D("P1D");

	private String code;

	Resolution(String code) {
		this.code = code;
	}

	public Period period() {
		return Period.parse(code);
	}
}
