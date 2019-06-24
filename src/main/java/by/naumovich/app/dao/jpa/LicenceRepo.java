package by.naumovich.app.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.naumovich.app.dao.model.DriverLicence;

@Repository
public interface LicenceRepo extends JpaRepository<DriverLicence, Integer> {

	DriverLicence findByUserIdAndId(Integer userId, Integer id);

	List<DriverLicence> findByUserId(Integer userId);
}
