package hu.uni.eku.tzs.dao;

import hu.uni.eku.tzs.dao.entity.MoviesGenresEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesGenresRepository extends JpaRepository<MoviesGenresEntity, Integer> {
}
