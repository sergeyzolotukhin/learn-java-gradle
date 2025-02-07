package ua.in.szolotukhin.jackson.other.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SchedulesFilter {
	private List<String> scheduleTypes;
	private Boolean activeVersion;
	private LocalDate startDate;
	private LocalDate endDate;

	public static SchedulesFilter activeOnly() {
		SchedulesFilter filter = new SchedulesFilter();
		filter.activeVersion = true;

		return filter;
	}
}
