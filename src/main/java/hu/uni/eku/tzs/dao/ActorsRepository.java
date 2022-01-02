package hu.uni.eku.tzs.dao;

import hu.uni.eku.tzs.dao.entity.ActorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorsRepository extends JpaRepository<ActorsEntity, Integer> {
}
