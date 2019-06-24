package by.naumovich.app.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.naumovich.app.dao.model.Car;
import by.naumovich.app.dao.model.IdAwareObject;
import by.naumovich.app.dao.validation.AdminRole;
import by.naumovich.app.filter.TokenRegFilter;

@RestController
@RequestMapping("/cars")
public class CarController extends SimpleCrudController<Car> {
	@Override
	public IdAwareObject create(@RequestHeader(name = TokenRegFilter.TOKEN) @AdminRole String token, @Valid Car obj) {
		return super.create(token, obj);
	}

	@Override
	public void update(@RequestHeader(name = TokenRegFilter.TOKEN) @AdminRole String token, @Valid Car obj) {
		super.update(token, obj);
	}

	@Override
	public void delete(@RequestHeader(name = TokenRegFilter.TOKEN) @AdminRole String token, List<Integer> ids) {
		super.delete(token, ids);
	}
}
