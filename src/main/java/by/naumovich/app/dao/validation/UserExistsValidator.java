package by.naumovich.app.dao.validation;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.Hibernate;

import by.naumovich.app.dao.model.User;

public class UserExistsValidator implements ConstraintValidator<UserExists, Integer> {

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        try {
            User one =
                RepoHolder.userRepo()
                    .getOne(id);

            Hibernate.initialize(one);
            return one.getId() == id;
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

}
