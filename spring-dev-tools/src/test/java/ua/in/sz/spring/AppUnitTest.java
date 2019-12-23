package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class AppUnitTest {

	@Test
	public void test() {
		ScheduleValueService service = Mockito.mock(ScheduleValueService.class, Mockito.withSettings().verboseLogging());

		Mockito.when(service.execute()).thenReturn("111");

		log.info("run: {}", service.execute());
	}
}
