package ua.in.szolotukhin.jackson.json;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.extern.slf4j.Slf4j;
import ua.in.szolotukhin.jackson.model.SchedulesFilter;

@Slf4j
public abstract class SchedulesFilterMixIn {
	@JsonCreator
	public static SchedulesFilter createSchedulesFilter() {
		throw new UnsupportedOperationException("factory method invoked mixin");
	}
}
