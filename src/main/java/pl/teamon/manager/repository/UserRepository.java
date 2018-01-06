package pl.teamon.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.teamon.manager.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByEmail(String email);

}
