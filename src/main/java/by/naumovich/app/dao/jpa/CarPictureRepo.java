package by.naumovich.app.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.naumovich.app.dao.model.CarPicture;

@Repository
public interface CarPictureRepo extends JpaRepository<CarPicture, Integer> {

	List<CarPicture> findByCarId(Integer carId);
}
