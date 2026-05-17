package com.company.networkmovers.modules.mover.repository;

import com.company.networkmovers.modules.mover.entity.MoverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoverRepository extends JpaRepository<MoverEntity, Long> {
}
