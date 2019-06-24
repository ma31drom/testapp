package by.naumovich.app.dao.validation;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Hibernate;

import by.naumovich.app.dao.model.User;
import by.naumovich.app.excep.Unauthorized;
import by.naumovich.app.filter.TokenRegFilter;
import by.naumovich.app.service.impl.CredsServiceImpl;
import by.naumovich.app.service.impl.CredsServiceImpl.UserAndDate;

public class AuthValidations {

	public AuthValidations() {
		// TODO Auto-generated constructor stub
	}

	static public void validateUser() {
		try {
			User findById = user();

			if (!findById.getRole().equals(by.naumovich.app.dao.model.UserRole.user))
				throw new Unauthorized();
		} catch (EntityNotFoundException e) {
			throw new Unauthorized(e);
		}
	}

	private static User user() {
		Integer userId = CredsServiceImpl.getUserId();

		User findById = RepoHolder.userRepo().getOne(userId);

		Hibernate.initialize(findById);
		return findById;
	}

	static public void validateAdmin() {
		try {
			User findById = user();

			if (!findById.getRole().equals(by.naumovich.app.dao.model.UserRole.admin))
				throw new Unauthorized();
		} catch (EntityNotFoundException e) {
			throw new Unauthorized(e);
		}
	}

	static public void validateLogged() {
		try {
			User findById = user();

			if (findById == null)
				throw new Unauthorized();
		} catch (EntityNotFoundException e) {
			throw new Unauthorized(e);
		}
	}
}
