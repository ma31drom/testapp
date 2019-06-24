package by.naumovich.app.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.naumovich.app.dao.model.IdAwareObject;
import by.naumovich.app.dao.model.Model;
import by.naumovich.app.dao.validation.AuthValidations;
import by.naumovich.app.filter.TokenRegFilter;

@RestController
@RequestMapping("/models")
public class ModelController extends SimpleCrudController<Model> {

	@Override
	public IdAwareObject create(@RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token,
			@RequestBody @Valid Model obj) {
		AuthValidations.validateAdmin();
		return super.create(token, obj);
	}

	@Override
	public void update(@RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token,
			@RequestBody @Valid Model obj) {

		AuthValidations.validateAdmin();
		super.update(token, obj);
	}

	@Override
	public void delete(@RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token,
			@RequestParam List<Integer> ids) {
		AuthValidations.validateAdmin();
		super.delete(token, ids);
	}
}
