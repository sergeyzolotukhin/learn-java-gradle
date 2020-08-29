package ua.in.sz.tabular.domain;

import java.time.Duration;

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

	public Duration duration() {
		return Duration.parse(code);
	}
}
