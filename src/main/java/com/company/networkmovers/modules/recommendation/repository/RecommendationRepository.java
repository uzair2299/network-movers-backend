package com.company.networkmovers.modules.recommendation.repository;

import java.util.UUID;

import com.company.networkmovers.modules.recommendation.entity.RecommendationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRepository extends JpaRepository<RecommendationEntity, UUID> {
}
