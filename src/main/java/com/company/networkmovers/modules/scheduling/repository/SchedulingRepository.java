package com.company.networkmovers.modules.scheduling.repository;

import com.company.networkmovers.modules.scheduling.entity.SchedulingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulingRepository extends JpaRepository<SchedulingEntity, Long> {
}
