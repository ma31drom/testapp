package by.naumovich.app.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import by.naumovich.app.dao.model.IdAwareObject;
import by.naumovich.app.service.CrudService;

public abstract class AbstractCrudService<T extends IdAwareObject> implements CrudService<T> {

    @Autowired
    protected JpaRepository<T, Integer> repo;

    @Override
    public T get(Integer id) {

        T one = repo.getOne(id);
        Hibernate.initialize(one);
        return one;
    }

    @Override
    public List<T> getAll() {
        return repo.findAll();
    }

    @Override
    public T create(T obj) {
        return repo.save(obj);
    }

    @Override
    public T update(T obj) {
        if (repo.getOne(obj.getId()) != null) {
            return repo.save(obj);
        }
        throw new EntityNotFoundException();
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }

}
