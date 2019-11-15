package by.naumovich.app.service;

import java.util.List;

import by.naumovich.app.dao.model.Model;

public interface ModelService extends CrudService<Model> {

    List<Model> getByBrand(Integer brandId);

}
