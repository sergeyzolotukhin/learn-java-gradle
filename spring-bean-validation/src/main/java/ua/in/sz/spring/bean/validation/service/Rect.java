package ua.in.sz.spring.bean.validation.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Builder
@Getter
@AllArgsConstructor(staticName = "of")
public class Rect {
	@Min(value = 0, message = "X should not be less than 0")
	@Max(value = 1024, message = "X should not be greater than 1024")
	private int x;

	@Min(value = 0, message = "Y should not be less than 0")
	@Max(value = 1024, message = "Y should not be greater than 1024")
	private int y;

	@Min(value = 0, message = "width should not be less than 0")
	@Max(value = 1024, message = "width should not be greater than 1024")
	private int width;

	@Min(value = 0, message = "height should not be less than 0")
	@Max(value = 1024, message = "height should not be greater than 1024")
	private int height;
}
