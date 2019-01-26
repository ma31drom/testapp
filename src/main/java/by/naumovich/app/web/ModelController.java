package by.naumovich.app.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.naumovich.app.dao.model.Model;

@RestController
@RequestMapping("/models")
public class ModelController extends SimpleCrudController<Model> {

}
