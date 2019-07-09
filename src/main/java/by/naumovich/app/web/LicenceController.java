package by.naumovich.app.web;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import by.naumovich.app.dao.model.DriverLicence;
import by.naumovich.app.dao.model.IdAwareObject;
import by.naumovich.app.dao.validation.AuthValidations;
import by.naumovich.app.excep.Unauthorized;
import by.naumovich.app.filter.TokenRegFilter;
import by.naumovich.app.service.LicenceService;
import by.naumovich.app.service.impl.CredsServiceImpl;

@RestController
@RequestMapping("/licences")
public class LicenceController extends ErrorHandlingController {

    @Autowired
    LicenceService service;

    @GetMapping(path = "/{id}")
    @ResponseBody
    public DriverLicence get(@PathVariable Integer id) {
        return service.getForUser(id);
    }

    @GetMapping
    @ResponseBody
    public DriverLicence getAll(@RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token) {

        Integer userId = CredsServiceImpl.getUserId();
        if (userId == null)
            throw new Unauthorized();
        return service.getAllForUser();
    }

    @GetMapping("/admin")
    @ResponseBody
    public List<DriverLicence> getAllAdmin(@RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token) {
        AuthValidations.validateAdmin();
        return service.getAll();
    }

    @PutMapping(path = "/{id}")
    public void update(
        @RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token,
        @PathVariable Integer id,
        @RequestBody @Valid DriverLicence obj) {

        if (AuthValidations.isAdmin()) {
            if (obj.getId() != id)
                throw new ValidationException("path and body id mismatch");
            obj.setId(id);
            service.update(obj);
        } else if (obj.getUserId() != CredsServiceImpl.getUserId()) {
            throw new Unauthorized();
        }
        if (obj.getId() != id)
            throw new ValidationException("path and body id mismatch");
        obj.setId(id);
        service.update(obj);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token, @PathVariable Integer id) {
        if (AuthValidations.isAdmin()) {
            service.deleteForUser(Arrays.asList(id));
        } else {
            DriverLicence forUser = service.getForUser(CredsServiceImpl.getUserId());
            if (forUser.getId() != id) {
                throw new Unauthorized();
            }
            service.deleteForUser(Arrays.asList(id));
        }
    }

    @PostMapping
    @ResponseBody
    public IdAwareObject create(@RequestBody @Valid DriverLicence obj) {
        Integer userId = CredsServiceImpl.getUserId();
        if (userId != obj.getUserId())
            throw new Unauthorized();
        IdAwareObject result = new IdAwareObject();
        BeanUtils.copyProperties(service.create(obj), result);
        return result;
    }

}
