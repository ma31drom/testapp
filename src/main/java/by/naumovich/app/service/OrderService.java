package by.naumovich.app.service;

import java.util.List;

import by.naumovich.app.dao.model.Order;

public interface OrderService extends CrudService<Order> {

	Order getForUser(Integer userId, Integer id);

	List<Order> getAllForUser(Integer userId);

}
