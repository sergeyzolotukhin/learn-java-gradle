package ua.in.sz.bean.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class BigRectValidator implements ConstraintValidator<BigRect, Integer> {
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return value != null && value > 10;
	}
}
