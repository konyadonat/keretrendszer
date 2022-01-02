package hu.uni.eku.tzs.dao;

import hu.uni.eku.tzs.dao.entity.MoviesDirectorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesDirectorsRepository extends JpaRepository<MoviesDirectorsEntity, Integer> {
}
