package com.company.networkmovers.modules.optimization.repository;

import com.company.networkmovers.modules.optimization.entity.OptimizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptimizationRepository extends JpaRepository<OptimizationEntity, Long> {
}
