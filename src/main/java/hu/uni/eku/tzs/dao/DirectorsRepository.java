package hu.uni.eku.tzs.dao;

import hu.uni.eku.tzs.dao.entity.DirectorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorsRepository extends JpaRepository<DirectorsEntity, Integer> {
}
