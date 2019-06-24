package by.naumovich.app.service.impl;

import org.springframework.stereotype.Service;

import by.naumovich.app.dao.model.User;
import by.naumovich.app.service.UserService;

@Service
public class UserJpaService extends AbstractCrudService<User> implements UserService {

}
