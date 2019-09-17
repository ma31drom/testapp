package by.naumovich.app.dao.validation;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.Hibernate;

import by.naumovich.app.dao.model.Car;

public class CarExistsValidator implements ConstraintValidator<CarExists, Integer> {

    @Override
    public boolean isValid(Integer brandId, ConstraintValidatorContext context) {
        try {
            Car one =
                RepoHolder.carRepo()
                    .getOne(brandId);
            Hibernate.initialize(one);
            return one.getId() == brandId;
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

}
