package by.naumovich.app.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.naumovich.app.dao.model.Brand;
import by.naumovich.app.dao.model.IdAwareObject;
import by.naumovich.app.dao.validation.AdminRole;
import by.naumovich.app.filter.TokenRegFilter;

@RestController
@RequestMapping("/brands")
public class BrandController extends SimpleCrudController<Brand> {
	@Override
	public IdAwareObject create(@RequestHeader(name = TokenRegFilter.TOKEN) @AdminRole String token,
			@RequestBody @Valid Brand obj) {
		return super.create(token, obj);
	}

	@Override
	public void update(@RequestHeader(name = TokenRegFilter.TOKEN) @AdminRole String token,
			@RequestBody @Valid Brand obj) {
		super.update(token, obj);
	}

	@Override
	public void delete(@RequestHeader(name = TokenRegFilter.TOKEN) @AdminRole String token,
			@RequestParam List<Integer> ids) {
		super.delete(token, ids);
	}
}
