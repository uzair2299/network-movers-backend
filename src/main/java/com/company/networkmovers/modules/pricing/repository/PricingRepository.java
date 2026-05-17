package com.company.networkmovers.modules.pricing.repository;

import com.company.networkmovers.modules.pricing.entity.PricingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingRepository extends JpaRepository<PricingEntity, Long> {
}
