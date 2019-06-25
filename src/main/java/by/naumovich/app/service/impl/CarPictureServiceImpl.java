package by.naumovich.app.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.naumovich.app.dao.jpa.CarPictureRepo;
import by.naumovich.app.dao.model.CarPicture;
import by.naumovich.app.dao.model.IdAwareObject;
import by.naumovich.app.excep.CarNotFoundException;
import by.naumovich.app.excep.CarPicNotFoundException;
import by.naumovich.app.service.CarPictureService;
import by.naumovich.app.service.CarService;

@Service
public class CarPictureServiceImpl implements CarPictureService {

	@Autowired
	CarPictureRepo repo;
	@Autowired
	CarService carService;

	@Override
	public void delete(Integer carId, Integer pictureId) {
		repo.deleteById(pictureId);
	}

	@Override
	public IdAwareObject create(Integer carId, String fileName, InputStream is) {
		if (carService.get(carId) == null)
			throw new CarNotFoundException();

		try {

			byte[] byteArray = IOUtils.toByteArray(is);

			CarPicture cp = new CarPicture();
			cp.setCarId(carId);
			cp.setValueBase64(byteArray);
			cp.setFileName(fileName);
			CarPicture save = repo.save(cp);
			return new IdAwareObject(save.getId());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public CarPicture getById(Integer carId, Integer pictureId) {

		CarPicture one = repo.getOne(pictureId);
		if (one == null || one.getCarId() != carId) {
			throw new CarPicNotFoundException();
		}

		return one;
	}

	@Override
	public List<CarPicture> getList(Integer carId) {
		return repo.findByCarId(carId);
	}

}
