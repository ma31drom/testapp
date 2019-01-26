package by.naumovich.app.service;

import java.util.List;

import by.naumovich.app.dao.model.IdAwareObject;

public interface CrudService<T extends IdAwareObject> {

    T get(Integer id);

    List<T> getAll();

    T create(T obj);

    T update(T obj);

    void delete(Integer id);

}
