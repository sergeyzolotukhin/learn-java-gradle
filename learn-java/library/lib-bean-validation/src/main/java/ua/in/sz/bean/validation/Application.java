package ua.in.sz.bean.validation;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.bean.validation.model.Rect;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Slf4j
public class Application {
	public static void main(String[] args) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Rect rect = Rect.builder().x(-10).y(-15).height(2000).width(-1).build();

		Set<ConstraintViolation<Rect>> violations = validator.validate(rect);

		violations.forEach(v -> log.info("Violation: [{}]", v.getMessage()));
	}
}
