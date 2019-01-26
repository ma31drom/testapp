package by.naumovich.app.service.impl;

import org.springframework.stereotype.Service;

import by.naumovich.app.dao.model.Model;
import by.naumovich.app.service.ModelService;

@Service
public class ModelJpaService extends AbstractCrudService<Model> implements ModelService {

}
