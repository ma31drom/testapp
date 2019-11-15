package by.naumovich.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import by.naumovich.app.dao.jpa.ModelRepo;
import by.naumovich.app.dao.model.Model;
import by.naumovich.app.service.ModelService;

@Service
public class ModelJpaService extends AbstractCrudService<Model> implements ModelService {

    @Override
    public List<Model> getByBrand(Integer brandId) {
        return ((ModelRepo) repo).findByBrand(brandId);
    }

}
