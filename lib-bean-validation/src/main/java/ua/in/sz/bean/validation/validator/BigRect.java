package ua.in.sz.bean.validation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = BigRectValidator.class)
@Documented
public @interface BigRect {
	String message() default "Value should be more 10";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
