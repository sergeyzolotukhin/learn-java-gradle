package ua.in.sz.bean.validation.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class BigRectValidator implements ConstraintValidator<BigRect, Integer> {
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return value != null && value > 10;
	}
}
