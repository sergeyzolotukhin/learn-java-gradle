package ua.in.sz.notcomplited.schedules.builder;

import org.joda.time.DateTime;
import ua.in.sz.notcomplited.schedules.domain.ScheduleVO;
import ua.in.sz.notcomplited.schedules.domain.ScheduleValueVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class ScheduleValueVOListBuilder {
	private DateTime start;
	private DateTime end;
	private ScheduleVO schedule;
	private List<Object> values = new ArrayList<>();

	public ScheduleValueVOListBuilder start(DateTime start) {
		this.start = start;
		return this;
	}

	public ScheduleValueVOListBuilder end(DateTime end) {
		this.end = end;
		return this;
	}

	public ScheduleValueVOListBuilder schedule(ScheduleVO schedule) {
		this.schedule = schedule;
		return this;
	}

	public ScheduleValueVOListBuilder values(Object ... values) {
		this.values.addAll(Arrays.asList(values));
		return this;
	}

	public List<ScheduleValueVO<?>> build() {
		return values.stream()
				.map(this::build)
				.collect(toList());
	}

	public static ScheduleValueVOListBuilder builder() {
		return new ScheduleValueVOListBuilder();
	}

	public ScheduleValueVO<?> build(Object value) {
		DateTime start = Optional.ofNullable(this.start).orElse(schedule.getStart());
		DateTime end = Optional.ofNullable(this.end).orElse(schedule.getEnd());

		return ScheduleValueVO.builder()
				.start(start)
				.end(end)
				.schedule(schedule)
				.value(value)
				.build();
	}
}
