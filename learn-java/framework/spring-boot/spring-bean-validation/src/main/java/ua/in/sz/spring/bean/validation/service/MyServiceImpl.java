package ua.in.sz.spring.bean.validation.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

@Slf4j
@Component
@Validated
public class MyServiceImpl implements MyService {
	@Override
	public void invoke(@Valid Rect rect) {
		log.info("Service is invoked");
	}
}
