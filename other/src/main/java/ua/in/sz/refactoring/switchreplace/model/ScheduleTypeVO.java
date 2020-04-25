package ua.in.sz.refactoring.switchreplace.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
public class ScheduleTypeVO {
	private String code;
	private String name;
}
