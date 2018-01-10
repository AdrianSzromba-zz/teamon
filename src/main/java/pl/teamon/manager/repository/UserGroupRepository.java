package pl.teamon.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.teamon.manager.entity.UserGroup;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long>{

	List<UserGroup> findAllByOrderByIdAsc();
}
