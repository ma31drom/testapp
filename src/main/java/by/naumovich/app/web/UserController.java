package by.naumovich.app.web;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.InitializingBean;
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
import org.springframework.web.bind.annotation.RestController;

import by.naumovich.app.dao.model.Credentials;
import by.naumovich.app.dao.model.IdAwareObject;
import by.naumovich.app.dao.model.User;
import by.naumovich.app.dao.model.UserRole;
import by.naumovich.app.dao.validation.AuthValidations;
import by.naumovich.app.excep.Unauthorized;
import by.naumovich.app.filter.TokenRegFilter;
import by.naumovich.app.service.LoginService;
import by.naumovich.app.service.UserService;
import by.naumovich.app.service.impl.CredsServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController extends ErrorHandlingController implements InitializingBean {

    @Autowired
    UserService userService;

    @Autowired
    LoginService service;

    @PostMapping
    public IdAwareObject create(
        @RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token,
        @Valid @RequestBody User obj) {
        AuthValidations.isAdmin();
        return new IdAwareObject(userService.create(obj)
            .getId());
    }

    @PutMapping
    public void update(@RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token, @Valid @RequestBody User obj) {
        if (AuthValidations.isAdmin() || CredsServiceImpl.getUserId() == obj.getId()) {
            userService.update(obj);
        }
        throw new Unauthorized();
    }

    @DeleteMapping
    public void delete(
        @RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token,
        @RequestParam List<Integer> ids) {
        AuthValidations.validateAdmin();
        ids.forEach(id -> Optional.ofNullable(userService.get(id))
            .ifPresent(user -> userService.delete(user.getId())));

    }

    @GetMapping(path = "/{id}")
    public User get(@RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token, @PathVariable Integer id) {
        if (CredsServiceImpl.getUserId() == id) {
            return userService.get(id);
        } else
            throw new Unauthorized();
    }

    @GetMapping
    public List<User> getAll(@RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token) {
        AuthValidations.validateAdmin();
        return userService.getAll();
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        if (!userService.getAll()
            .stream()
            .filter(u -> u.getAddress() == "slkjdhfglkjha3187465" && u.getFirstName() == "MaxMax" && u.getLastName() == "MaxMax")
            .findAny()
            .isPresent()) {

            User obj = new User();
            obj.setAddress("slkjdhfglkjha3187465");
            obj.setFirstName("MaxMax");
            obj.setLastName("MaxMax");

            obj.setBirthDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2000-01-19 03:14:07"));
            obj.setRole(UserRole.admin);
            User create = userService.create(obj);

            Credentials creds = new Credentials();
            // string123
            creds.setPassHash("0362795b2ee7235b3b4d28f0698a85366703eacf0ba4085796ffd980d7653337");
            creds.setUserName("string");
            creds.setUserId(create.getId());
            service.save(creds);
        }
    }
}
