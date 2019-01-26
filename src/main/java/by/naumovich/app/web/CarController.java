package by.naumovich.app.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.naumovich.app.dao.model.Car;

@RestController
@RequestMapping("/cars")
public class CarController extends SimpleCrudController<Car> {

}
