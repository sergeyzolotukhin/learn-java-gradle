package ua.in.sz.refactoring.switchreplace.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
public class ScheduleVO {
	private ScheduleTypeVO scheduleType;
	private String identification;
}
