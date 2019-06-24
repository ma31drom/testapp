package by.naumovich.app.service.impl;

import org.springframework.stereotype.Service;

import by.naumovich.app.dao.model.Order;
import by.naumovich.app.service.OrderService;

@Service
public class OrderJpaService extends AbstractCrudService<Order> implements OrderService {

}
