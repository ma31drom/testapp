package by.naumovich.app.service;

import java.io.InputStream;
import java.util.List;

import by.naumovich.app.dao.model.CarPicture;
import by.naumovich.app.dao.model.IdAwareObject;

public interface CarPictureService {

	CarPicture getById(Integer carId, Integer pictureId);

	List<CarPicture> getList(Integer carId);

	void delete(Integer carId, Integer pictureId);

	IdAwareObject create(Integer carId, String fileName, InputStream is);

}
