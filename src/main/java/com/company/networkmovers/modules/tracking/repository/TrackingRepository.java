package com.company.networkmovers.modules.tracking.repository;

import com.company.networkmovers.modules.tracking.entity.TrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingRepository extends JpaRepository<TrackingEntity, Long> {
}
