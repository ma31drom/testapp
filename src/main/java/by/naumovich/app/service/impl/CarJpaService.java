package by.naumovich.app.service.impl;

import org.springframework.stereotype.Service;

import by.naumovich.app.dao.model.Car;
import by.naumovich.app.service.CarService;

@Service
public class CarJpaService extends AbstractCrudService<Car> implements CarService {

}
