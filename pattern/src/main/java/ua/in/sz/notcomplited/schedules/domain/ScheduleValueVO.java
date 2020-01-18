package ua.in.sz.notcomplited.schedules.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

@Slf4j
@Getter
@Builder
@ToString
public class ScheduleValueVO<T> {
	@ToString.Exclude
	private ScheduleVO schedule;

	private DateTime start;
	private DateTime end;

	private T value;
}
