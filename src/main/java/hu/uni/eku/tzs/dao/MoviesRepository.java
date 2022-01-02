package hu.uni.eku.tzs.dao;

import hu.uni.eku.tzs.dao.entity.MoviesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<MoviesEntity, Integer> {
}
