package pl.teamon.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.teamon.manager.entity.Trening;

public interface TreningRepository extends JpaRepository<Trening, Long>{

	List<Trening> findAllByOrderByDateAsc();
}
