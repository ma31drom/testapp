package by.naumovich.app.web;

import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import by.naumovich.app.dao.model.DriverLicence;
import by.naumovich.app.dao.model.IdAwareObject;
import by.naumovich.app.service.LicenceService;

@RestController
@RequestMapping("/licences")
public class LicenceController {

	@Autowired
	LicenceService service;

	@GetMapping(path = "/{id}")
	@ResponseBody
	public DriverLicence get(@PathVariable Integer id) {
		return service.getForUser(id);
	}

	@GetMapping
	@ResponseBody
	public List<DriverLicence> getAll() {
		return service.getAllForUser();
	}

	@GetMapping("/licences")
	@ResponseBody
	public List<DriverLicence> getAllAdmin() {
		return service.getAll();
	}
	
	@PutMapping(path = "/{id}")
	public void update(@PathVariable Integer id, @RequestBody @Valid DriverLicence obj) {

		if (obj.getId() != id)
			throw new ValidationException("path and body id mismatch");
		obj.setId(id);
		service.update(obj);
	}

	@DeleteMapping
	public void delete(@RequestParam List<Integer> ids) {
		service.deleteForUser(ids);
	}

	@PostMapping
	@ResponseBody
	public IdAwareObject create(@RequestBody @Valid DriverLicence obj) {
		IdAwareObject result = new IdAwareObject();
		BeanUtils.copyProperties(service.create(obj), result);
		return result;
	}

}
