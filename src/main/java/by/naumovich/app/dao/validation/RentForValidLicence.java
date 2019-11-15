package by.naumovich.app.dao.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;
import java.util.List;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.springframework.beans.BeanWrapperImpl;

import by.naumovich.app.dao.model.DriverLicence;
import by.naumovich.app.dao.model.OrderType;
import by.naumovich.app.dao.model.User;

@Constraint(validatedBy = RentForValidLicence.RentForValidLicenceValidator.class)
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RentForValidLicence {

    String message() default "Cron is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public static class RentForValidLicenceValidator implements ConstraintValidator<RentForValidLicence, Object> {

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            try {

                OrderType typeField = (OrderType) new BeanWrapperImpl(value).getPropertyValue("type");

                User one =
                    RepoHolder.userRepo()
                        .getOne((Integer) new BeanWrapperImpl(value).getPropertyValue("userId"));

                if (OrderType.rent == typeField) {
                    List<DriverLicence> findByUserId =
                        RepoHolder.userLicenceRepo()
                            .findByUserId(one.getId());

                    return findByUserId.stream()
                        .anyMatch(lic -> lic.getFrom()
                            .before(new Date())
                                         && lic.getTo()
                                             .after(new Date()));
                } else {
                    return true;
                }
            } catch (Exception e) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
                return false;
            }
        }

    }
}
