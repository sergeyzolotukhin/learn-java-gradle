package ua.in.sz.contex.resolver.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@ToString
public class ScheduleValue {
	private Interval interval;
	private int value;
}
