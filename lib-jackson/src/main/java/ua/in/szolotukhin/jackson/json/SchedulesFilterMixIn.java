package ua.in.szolotukhin.jackson.json;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class SchedulesFilterMixIn {
	@JsonCreator
	public static void activeOnly() {
	}
}
