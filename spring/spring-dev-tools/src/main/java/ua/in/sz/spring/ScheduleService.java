package ua.in.sz.spring;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Service
public class ScheduleService {

	@Value("classpath:inventory.txt")
	private Resource resource;
	@Autowired
	private ScheduleValueService scheduleValueService;

	@SneakyThrows
	@Scheduled(fixedRate = 1000)
	public void schedule() {
		String text = IOUtils.toString(resource.getInputStream(), UTF_8.name());
		String result = scheduleValueService.execute();
		log.info("Executed: [{}], [{}]", text, result);

		log.warn("End");
	}
}
