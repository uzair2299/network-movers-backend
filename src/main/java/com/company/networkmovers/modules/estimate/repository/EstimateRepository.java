package com.company.networkmovers.modules.estimate.repository;

import java.util.UUID;

import com.company.networkmovers.modules.estimate.entity.EstimateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstimateRepository extends JpaRepository<EstimateEntity, UUID> {
}
