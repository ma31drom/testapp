package by.naumovich.app.service;

import java.util.List;

import by.naumovich.app.dao.model.DriverLicence;

public interface LicenceService extends CrudService<DriverLicence> {

	DriverLicence getForUser(Integer userId);

	DriverLicence getAllForUser();

	void deleteForUser(List<Integer> ids);

}
