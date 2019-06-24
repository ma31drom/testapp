package by.naumovich.app.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.naumovich.app.dao.model.IdAwareObject;
import by.naumovich.app.dao.model.Model;
import by.naumovich.app.dao.model.User;
import by.naumovich.app.dao.validation.AdminRole;
import by.naumovich.app.filter.TokenRegFilter;
import by.naumovich.app.service.LoginService;
import by.naumovich.app.service.UserService;
import by.naumovich.app.service.impl.CredsServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController extends SimpleCrudController<User> {
	UserService userService;
	LoginService credsService;

	@Override
	public IdAwareObject create(@RequestHeader(required = false) String token, @RequestBody @Valid User obj) {
		// TODO Auto-generated method stub
		return super.create(token, obj);
	}
}
