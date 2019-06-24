package by.naumovich.app.dao.validation;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.Hibernate;

import by.naumovich.app.dao.model.User;
import by.naumovich.app.excep.Unauthorized;
import by.naumovich.app.service.impl.CredsServiceImpl;
import by.naumovich.app.service.impl.CredsServiceImpl.UserAndDate;

public class UserRoleValidator implements ConstraintValidator<UserRole, String> {

	@Override
	public boolean isValid(String userToken, ConstraintValidatorContext context) {
		try {
			UserAndDate userAndDate = CredsServiceImpl.lastActionMap.get(userToken);

			if (userAndDate == null)
				throw new Unauthorized();

			User findById = RepoHolder.userRepo().getOne(userAndDate.getUserId());

			Hibernate.initialize(findById);

			if (findById.getRole().equals(by.naumovich.app.dao.model.UserRole.user))
				return true;
			throw new Unauthorized();
		} catch (EntityNotFoundException e) {
			throw new Unauthorized(e);
		}
	}

}
