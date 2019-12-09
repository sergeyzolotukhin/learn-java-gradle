package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScheduleService {
//	@Scheduled(fixedRate = 1000)
	public void schedule() {
		log.info("Executed");
	}
}
