package by.naumovich.app.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.naumovich.app.dao.model.Brand;

@RestController
@RequestMapping("/brands")
public class BrandController extends SimpleCrudController<Brand>{

}
