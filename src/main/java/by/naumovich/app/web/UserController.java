package by.naumovich.app.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.naumovich.app.dao.model.User;
import by.naumovich.app.service.LoginService;
import by.naumovich.app.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController extends SimpleCrudController<User> {
	UserService userService;
	LoginService credsService;

}
