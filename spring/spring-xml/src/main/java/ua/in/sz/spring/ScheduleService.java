package ua.in.sz.spring;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class ScheduleService {

	@Setter
	private SimpleDateFormat simpleDateFormat;

	public void schedule() {
		log.info("Executed: [{}]", simpleDateFormat.format(new Date()));
	}
}
