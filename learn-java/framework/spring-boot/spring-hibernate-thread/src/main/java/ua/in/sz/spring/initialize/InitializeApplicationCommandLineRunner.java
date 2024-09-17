package ua.in.sz.spring.initialize;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.in.sz.spring.initialize.entity.InitializeScheduleEntity;
import ua.in.sz.spring.initialize.service.InitializeService;

import java.util.List;

@Slf4j
@Getter
@Component
@RequiredArgsConstructor
public class InitializeApplicationCommandLineRunner implements CommandLineRunner {

	private final InitializeService initializeService;

	@Override
	@SneakyThrows
	public void run(String...args) {
		log.info("Start application");

		List<InitializeScheduleEntity> entities = initializeService.load();

		for (InitializeScheduleEntity entity : entities) {
			log.info("Entity with name: [{}]", entity.getName());
			log.info("Actions: [{}]", entity.getActions());
		}

		log.info("End application");
	}
}