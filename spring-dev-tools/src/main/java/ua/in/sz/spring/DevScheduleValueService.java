package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@Profile("dev")
public class DevScheduleValueService implements ScheduleValueService {

	@PostConstruct
	public void init() {
		log.info("Init");
	}

	public String execute() {
		return "Dev";
	}
}
