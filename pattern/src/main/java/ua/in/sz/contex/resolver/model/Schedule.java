package ua.in.sz.contex.resolver.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@FieldNameConstants
@ToString(exclude = {"values"})
public class Schedule {
	private String name;
	private Interval interval;
	@Builder.Default
	private List<ScheduleValue> values = new ArrayList<>();
}
