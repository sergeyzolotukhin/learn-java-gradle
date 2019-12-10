package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("prod")
public class ProdScheduleValueService {

	@Scheduled(fixedRate = 2000)
	public void schedule() {
		String text = newMethods();
		log.info("Executed: [{}]", text);
	}

	private String newMethods() {
		log.info("New methods");

		return "N1";
	}
}
