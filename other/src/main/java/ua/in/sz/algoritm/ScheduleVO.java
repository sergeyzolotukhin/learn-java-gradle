package ua.in.sz.algoritm;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import static ua.in.sz.algoritm.ScheduleValueVO.FORMAT;

@Slf4j
@Getter
@Setter
@Builder
public class ScheduleVO {
	private DateTime start;
	private DateTime end;

	@Builder.Default
	private List<ScheduleValueVO<?>> values = new ArrayList<>();

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("\nSchedule ")
				.append(FORMAT.print(start))
				.append(" - ")
				.append(FORMAT.print(end))
				.append("\n");

		for (ScheduleValueVO<?> value : values) {
			sb.append("\t").append(value).append("\n");
		}

		return sb.toString();
	}
}
