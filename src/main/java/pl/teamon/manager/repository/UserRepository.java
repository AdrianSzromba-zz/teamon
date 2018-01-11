package pl.teamon.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.teamon.manager.entity.User;
import pl.teamon.manager.entity.UserGroup;

public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByEmail(String email);
	User findAllByUserGroupOrderByUsername(UserGroup GroupName);
	User findById(long parseLong);
//	@Query("select u from User u where u.firstname like %?1")
//	List<User> findByFirstnameEndsWith(String firstname);
}
