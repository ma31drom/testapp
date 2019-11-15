package by.naumovich.app.dao.validation;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Hibernate;

import by.naumovich.app.dao.model.User;
import by.naumovich.app.excep.Unauthorized;
import by.naumovich.app.service.impl.CredsServiceImpl;

public class AuthValidations {

    private AuthValidations() {
    }

    static public void validateUser() {
        try {
            User findById = user();

            if (!findById.getRole()
                .equals(by.naumovich.app.dao.model.UserRole.user))
                throw new Unauthorized();
        } catch (EntityNotFoundException e) {
            throw new Unauthorized(e);
        }
    }

    static public boolean isUser() {
        User user = user();
        return user.getRole()
            .equals(by.naumovich.app.dao.model.UserRole.user);
    }

    static public boolean isAdmin() {
        User user = user();
        return user.getRole()
            .equals(by.naumovich.app.dao.model.UserRole.admin);
    }

    public static User user() {
        Integer userId = CredsServiceImpl.getUserId();

        User findById =
            RepoHolder.userRepo()
                .getOne(userId);

        Hibernate.initialize(findById);
        return findById;
    }

    static public void validateAdmin() {
        try {
            User findById = user();

            if (!findById.getRole()
                .equals(by.naumovich.app.dao.model.UserRole.admin))
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
