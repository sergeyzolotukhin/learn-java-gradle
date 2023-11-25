package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ua.in.sz.spring.lib.ValueService;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@Profile("dev")
public class DevScheduleValueService implements ScheduleValueService {

	@PostConstruct
	public void init() {
		log.trace("Init");
	}

	@Override
	public String execute() {
		new ValueService().print();

		return "Dev";
	}
}
