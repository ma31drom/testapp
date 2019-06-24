package by.naumovich.app.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.naumovich.app.dao.model.Order;

//TODO /users/userId/orders
@RestController
@RequestMapping("/orders")
public class OrderController extends SimpleCrudController<Order> {

}
