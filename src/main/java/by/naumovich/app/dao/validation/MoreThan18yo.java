package by.naumovich.app.dao.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MoreThan18yoValidator.class)
@Documented
public @interface MoreThan18yo {
	String message() default "should be more than 18YO";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
