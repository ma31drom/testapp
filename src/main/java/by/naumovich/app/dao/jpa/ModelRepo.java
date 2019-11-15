package by.naumovich.app.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.naumovich.app.dao.model.Model;

@Repository
public interface ModelRepo extends JpaRepository<Model, Integer> {

    List<Model> findByBrand(Integer brand);

}
