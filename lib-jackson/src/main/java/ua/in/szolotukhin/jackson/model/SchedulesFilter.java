package ua.in.szolotukhin.jackson.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Builder
@Getter
@Setter
@ToString
//@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SchedulesFilter {
	private List<String> scheduleTypes;
	private Boolean activeVersion;

	public SchedulesFilter() {
		log.info("default constructor");
	}

	public static SchedulesFilter createSchedulesFilter() {
		log.info("factory method invoked");

		SchedulesFilter employee = new SchedulesFilter();
		employee.activeVersion = true;

		return employee;
	}
}
