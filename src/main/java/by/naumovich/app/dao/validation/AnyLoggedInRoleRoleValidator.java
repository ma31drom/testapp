package by.naumovich.app.dao.validation;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.Hibernate;

import by.naumovich.app.dao.model.User;
import by.naumovich.app.excep.Unauthorized;
import by.naumovich.app.service.impl.CredsServiceImpl;
import by.naumovich.app.service.impl.CredsServiceImpl.UserAndDate;

public class AnyLoggedInRoleRoleValidator implements ConstraintValidator<AnyLoggedInRole, String> {

	@Override
	public boolean isValid(String userToken, ConstraintValidatorContext context) {
		try {
			UserAndDate userAndDate = CredsServiceImpl.lastActionMap.get(userToken);

			if (userAndDate == null)
				throw new Unauthorized();

			User findById = RepoHolder.userRepo().getOne(userAndDate.getUserId());

			Hibernate.initialize(findById);

			return true;
		} catch (EntityNotFoundException e) {
			throw new Unauthorized(e);
		}
	}

}
