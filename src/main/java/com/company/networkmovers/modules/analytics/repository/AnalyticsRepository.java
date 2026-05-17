package com.company.networkmovers.modules.analytics.repository;

import com.company.networkmovers.modules.analytics.entity.AnalyticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyticsRepository extends JpaRepository<AnalyticsEntity, Long> {
}
