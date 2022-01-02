package hu.uni.eku.tzs.dao;

import hu.uni.eku.tzs.dao.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<RolesEntity, Integer> {
}
