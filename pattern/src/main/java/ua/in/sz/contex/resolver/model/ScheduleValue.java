package ua.in.sz.contex.resolver.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Getter
@Builder
@ToString
public class ScheduleValue {
	private LocalDateTime from;
	private LocalDateTime to;
	private int value;
}
