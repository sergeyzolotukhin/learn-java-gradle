package ua.in.sz.bean.validation.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ua.in.sz.bean.validation.validator.BigRect;


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

	@BigRect
	@Min(value = 0, message = "width should not be less than 0")
	@Max(value = 1024, message = "width should not be greater than 1024")
	private int width;

	@BigRect
	@Min(value = 0, message = "height should not be less than 0")
	@Max(value = 1024, message = "height should not be greater than 1024")
	private int height;
}
