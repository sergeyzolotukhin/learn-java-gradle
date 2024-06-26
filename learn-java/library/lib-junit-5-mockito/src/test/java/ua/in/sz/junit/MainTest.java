package ua.in.sz.junit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Mockito;

import java.util.List;

@Slf4j
@SuppressWarnings("rawtypes")
class MainTest {
	@Test
	void jsonAssert() {
		List mockedList = Mockito.mock(List.class);

		Mockito.when(mockedList.get(AdditionalMatchers.geq(10))).thenReturn("first");

		log.info("get by index: [{}]", mockedList.get(15));
	}
}
