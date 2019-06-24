package by.naumovich.app.dao.validation;

import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MoreThan18yoValidator implements ConstraintValidator<MoreThan18yo, Date> {

	@Override
	public boolean isValid(Date bitrth, ConstraintValidatorContext context) {
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.YEAR, -18);
		Date y18ago = instance.getTime();
		return bitrth.before(y18ago);
	}

}
