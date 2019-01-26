package by.naumovich.app.dao.validation;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.Hibernate;

import by.naumovich.app.dao.model.Model;

public class ModelExistsValidator implements ConstraintValidator<ModelExists, Integer> {

    @Override
    public boolean isValid(Integer brandId, ConstraintValidatorContext context) {
        try {
            Model one =
                RepoHolder.modelRepo()
                    .getOne(brandId);
            Hibernate.initialize(one);
            return one.getId() == brandId;
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

}
