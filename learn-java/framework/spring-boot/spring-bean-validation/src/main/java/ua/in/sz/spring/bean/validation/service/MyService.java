package ua.in.sz.spring.bean.validation.service;

import jakarta.validation.Valid;

public interface MyService {
	void invoke(@Valid Rect rect);
}
