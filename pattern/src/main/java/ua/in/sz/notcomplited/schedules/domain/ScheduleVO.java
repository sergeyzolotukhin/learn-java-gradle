package ua.in.sz.notcomplited.schedules.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.util.List;

@Slf4j
@Getter
@Setter
@Builder
@ToString
public class ScheduleVO {
	private DateTime start;
	private DateTime end;
	private Resolution resolution;

	@ToString.Exclude
	private List<ScheduleValueVO<?>> values;
}
