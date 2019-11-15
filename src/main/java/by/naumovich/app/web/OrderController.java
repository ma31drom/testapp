package by.naumovich.app.web;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;

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

import by.naumovich.app.dao.model.IdAwareObject;
import by.naumovich.app.dao.model.Order;
import by.naumovich.app.dao.model.User;
import by.naumovich.app.dao.validation.AuthValidations;
import by.naumovich.app.excep.Unauthorized;
import by.naumovich.app.filter.TokenRegFilter;
import by.naumovich.app.service.OrderService;

@RestController
@RequestMapping("/users/{userId}/orders")
public class OrderController extends ErrorHandlingController {

    @Autowired
    OrderService orderService;

    @GetMapping(path = "/{id}")
    @ResponseBody
    public Order get(
        @RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token,
        @PathVariable Integer userId,
        @PathVariable Integer id) {
        return orderService.getForUser(userId, id);
    }

    @GetMapping
    @ResponseBody
    public List<Order> getForUser(
        @RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token,
        @PathVariable Integer userId) {

        if (AuthValidations.isUser()) {
            return orderService.getAllForUser(userId);
        }
        if (AuthValidations.isAdmin()) {
            return orderService.getAll();
        }

        return Collections.emptyList();
    }

    @PostMapping
    @ResponseBody
    public IdAwareObject create(
        @RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token,
        @PathVariable Integer userId,
        @RequestBody @Valid Order order) {

        if (order.getUserId() != userId) {
            throw new ValidationException();
        }

        return orderService.create(order);
    }

    @PutMapping
    @ResponseBody
    public IdAwareObject update(
        @RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token,
        @Valid @RequestBody Order obj) {
        AuthValidations.validateLogged();
        User user = AuthValidations.user();

        if (user.getId() != obj.getUserId()) {
            AuthValidations.validateAdmin();
        }

        return orderService.update(obj);

    }

    @DeleteMapping(path = "/{id}")
    @ResponseBody
    public void delete(
        @RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token,
        @PathVariable Integer userId,
        @PathVariable Integer id) {
        if (AuthValidations.isAdmin()
            || orderService.get(id)
                .getUserId()
                .equals(userId)) {
            orderService.delete(id);
        }
        throw new Unauthorized();
    }

}
