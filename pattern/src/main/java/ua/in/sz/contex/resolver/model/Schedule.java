package ua.in.sz.contex.resolver.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@ToString
public class Schedule {
	private LocalDateTime from;
	private LocalDateTime to;
	@Builder.Default
	private List<ScheduleValue> values = new ArrayList<>();
}
