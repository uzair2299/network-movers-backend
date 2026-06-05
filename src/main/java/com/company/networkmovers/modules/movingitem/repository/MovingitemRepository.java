package com.company.networkmovers.modules.movingitem.repository;

import java.util.UUID;

import com.company.networkmovers.modules.movingitem.entity.MovingitemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovingitemRepository extends JpaRepository<MovingitemEntity, UUID> {
}
