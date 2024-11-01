package ua.in.sz.bean.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.bean.validation.model.Rect;

import java.util.Set;

@Slf4j
public class ValidationApplication {
	public static void main(String[] args) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Rect rect = Rect.builder().x(-10).y(-15).height(2000).width(-1).build();

		Set<ConstraintViolation<Rect>> violations = validator.validate(rect);

		violations.forEach(v -> log.info("Violation: [{}]", v.getMessage()));
	}
}
