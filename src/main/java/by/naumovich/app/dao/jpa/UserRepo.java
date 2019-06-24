package by.naumovich.app.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.naumovich.app.dao.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
}
