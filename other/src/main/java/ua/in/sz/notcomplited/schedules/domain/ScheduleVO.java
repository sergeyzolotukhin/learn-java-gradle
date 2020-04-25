package ua.in.sz.notcomplited.schedules.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.util.ArrayList;
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
	@Builder.Default
	private List<ScheduleValueVO<?>> values = new ArrayList<>();

	public void addValues(List<ScheduleValueVO<?>> values) {
		this.values.addAll(values);
	}
}
