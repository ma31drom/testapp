package by.naumovich.app.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import by.naumovich.app.dao.model.CarPicture;
import by.naumovich.app.dao.validation.AuthValidations;
import by.naumovich.app.filter.TokenRegFilter;
import by.naumovich.app.service.CarPictureService;

@RestController
@RequestMapping("/cars")
public class CarPicController extends ErrorHandlingController {

	@Autowired
	CarPictureService service;

	@PostMapping("/{carId}/pictures")
	void save(@RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token, @PathVariable Integer carId,
			@RequestPart MultipartFile file) throws IOException {
		AuthValidations.validateAdmin();
		service.create(carId, file.getOriginalFilename(), file.getInputStream());

	}

	@GetMapping("/{carId}/pictures")
	List<CarPicture> get(@PathVariable Integer carId) {

		List<CarPicture> list = service.getList(carId);
		list.forEach(cp -> cp.setValueBase64(null));

		return list;
	}

	@GetMapping("/{carId}/pictures/{id}")
	void get(@PathVariable Integer carId, @PathVariable Integer id, HttpServletResponse response) throws IOException {
		CarPicture byId = service.getById(carId, id);
		byte[] decodeFromString = byId.getValueBase64();

		response.setContentType("application/octet-stream");
		response.addHeader("Content-disposition", "attachment; filename=" + byId.getFileName());

		response.getOutputStream().write(decodeFromString);
	}

	@DeleteMapping("/{carId}/pictures/{id}")
	void delete(@RequestHeader(name = TokenRegFilter.TOKEN, required = false) String token, @PathVariable Integer carId,
			@PathVariable Integer id) {
		AuthValidations.validateAdmin();
		service.delete(carId, id);
	}

}
