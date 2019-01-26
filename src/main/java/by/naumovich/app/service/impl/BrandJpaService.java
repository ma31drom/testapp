package by.naumovich.app.service.impl;

import org.springframework.stereotype.Service;

import by.naumovich.app.dao.model.Brand;
import by.naumovich.app.service.BrandService;

@Service
public class BrandJpaService extends AbstractCrudService<Brand> implements BrandService {

}
