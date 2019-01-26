package by.naumovich.app.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import by.naumovich.app.dao.model.IdAwareObject;
import by.naumovich.app.service.CrudService;

public abstract class SimpleCrudController<T extends IdAwareObject> extends ErrorHandlingController {

    @Autowired
    private CrudService<T> service;

    @GetMapping(path = "/{id}")
    @ResponseBody
    public T get(@PathVariable Integer id) {
        return service.get(id);
    }

    @GetMapping
    @ResponseBody
    public List<T> getAll() {
        return service.getAll();
    }

    @PutMapping
    public void update(@RequestBody @Valid T obj) {
        service.update(obj);
    }

    @DeleteMapping
    public void delete(@RequestParam List<Integer> ids) {
        ids.forEach(service::delete);
    }

    @PostMapping
    @ResponseBody
    public IdAwareObject create(@RequestBody @Valid T obj) {
        IdAwareObject result = new IdAwareObject();
        BeanUtils.copyProperties(service.create(obj), result);
        return result;
    }

}
