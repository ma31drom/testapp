package by.naumovich.app.dao.validation;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.Hibernate;

import by.naumovich.app.dao.model.Brand;

public class BrandExistsValidator implements ConstraintValidator<BrandExists, Integer> {

    @Override
    public boolean isValid(Integer brandId, ConstraintValidatorContext context) {
        try {
            Brand one =
                RepoHolder.brandRepo()
                    .getOne(brandId);
            Hibernate.initialize(one);
            return one.getId() == brandId;
        } catch (EntityNotFoundException e) {
            return false;

        }
    }

}
