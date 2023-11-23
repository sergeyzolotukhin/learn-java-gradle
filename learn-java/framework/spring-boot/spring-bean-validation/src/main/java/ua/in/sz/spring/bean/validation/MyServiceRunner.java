package ua.in.sz.spring.bean.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.in.sz.spring.bean.validation.service.MyService;
import ua.in.sz.spring.bean.validation.service.Rect;

import javax.validation.ConstraintViolationException;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyServiceRunner implements CommandLineRunner {
	private final MyService myService;

	@Override
	public void run(String... args) {
		try {
			Rect rect = Rect.builder().x(-10).y(-15).height(2000).width(-1).build();

			myService.invoke(rect);
		} catch (ConstraintViolationException ex) {
			log.warn("Can't invoke method: {}", ex.getMessage());
		}
	}
}
