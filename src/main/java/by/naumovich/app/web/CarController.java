package by.naumovich.app.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import by.naumovich.app.dao.model.Car;
import by.naumovich.app.dao.model.IdAwareObject;
import by.naumovich.app.dao.validation.AuthValidations;
import by.naumovich.app.filter.TokenRegFilter;
import by.naumovich.app.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController extends ErrorHandlingController {
    
    @Autowired
    CarService service;

    @PostMapping
    @ResponseBody
    public IdAwareObject create(
        @RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token,
        @RequestBody @Valid Car obj) {
        AuthValidations.validateAdmin();
        return service.create(obj);
    }

    @PutMapping
    public void update(@RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token, @RequestBody @Valid Car obj) {
        AuthValidations.validateAdmin();
        service.update(obj);
    }

    @DeleteMapping
    public void delete(
        @RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token,
        @RequestParam List<Integer> ids) {
        AuthValidations.validateAdmin();
        ids.forEach(id -> Optional.ofNullable(service.get(id))
            .ifPresent(obj -> service.delete(obj.getId())));
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public Car get(@RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token, @PathVariable Integer id) {
        return service.get(id);
    }

    @GetMapping
    @ResponseBody
    public List<Car> getAll(@RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token) {
        return service.getAll();
    }
}
