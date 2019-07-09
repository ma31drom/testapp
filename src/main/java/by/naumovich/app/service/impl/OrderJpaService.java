package by.naumovich.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.naumovich.app.dao.jpa.OrderRepo;
import by.naumovich.app.dao.model.Order;
import by.naumovich.app.service.OrderService;

@Service
public class OrderJpaService extends AbstractCrudService<Order> implements OrderService {

	@Autowired
	OrderRepo repo;

	@Override
	public Order getForUser(Integer userId, Integer id) {
		Order one = repo.getOne(id);
		if (one == null)
			return null;
		return one.getUserId() == userId ? one : null;
	}

	@Override
	public List<Order> getAllForUser(Integer userId) {
		return repo.findByUserId(userId);
	}

}
