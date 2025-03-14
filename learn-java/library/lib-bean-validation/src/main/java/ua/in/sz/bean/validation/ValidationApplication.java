package ua.in.sz.bean.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.bean.validation.model.Rect;
import ua.in.sz.bean.validation.validator.UpdateGroup;

import java.util.Set;

// https://docs.jboss.org/hibernate/validator/9.0/reference/en-US/html_single/#validator-gettingstarted
// https://medium.com/javarevisited/javax-validation-and-groups-acd9a1fb1091

@Slf4j
public class ValidationApplication {
	public static void main(String[] args) {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
			Validator validator = factory.getValidator();
			Rect rect = Rect.builder().x(-10).y(-15).height(2000).name("1").width(-1).build();

//			Set<ConstraintViolation<Rect>> violations = validator.validate(rect);
			Set<ConstraintViolation<Rect>> violations = validator.validate(rect, UpdateGroup.class);

			violations.forEach(v -> log.info("Violation: [{}]", v.getMessage()));
        }
	}
}
