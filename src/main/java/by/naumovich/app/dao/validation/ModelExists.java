package by.naumovich.app.dao.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ModelExistsValidator.class)
@Documented
public @interface ModelExists {
    String message() default "no such model";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
