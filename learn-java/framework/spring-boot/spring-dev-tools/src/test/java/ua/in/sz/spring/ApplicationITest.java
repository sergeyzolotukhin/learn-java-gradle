package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@Slf4j
@ExtendWith(SpringExtension.class)
//@SpringBootTest
class ApplicationITest {

	@TestConfiguration
	static class ScheduleTextConfig {
		@Bean
		public ScheduleService scheduleService() {
			return new ScheduleService();
		}
	}

	@MockBean
	private ScheduleValueService scheduleValueService;

	@Autowired
	private ScheduleService scheduleService;

	@BeforeEach
	public void setUp() {
		Mockito.when(scheduleValueService.execute()).thenReturn("serhij.zolotukhin");
	}

	@Test
	public void contextLoads() {
		scheduleService.schedule();
		scheduleService.schedule();
	}
}