package ua.in.sz.notcomplited.streamreturn;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

@Slf4j
@Getter
@Builder
@ToString
public class ScheduleVO {
	private DateTime start;
	private DateTime end;
}
