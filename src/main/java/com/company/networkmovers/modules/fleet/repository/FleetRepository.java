package com.company.networkmovers.modules.fleet.repository;

import com.company.networkmovers.modules.fleet.entity.FleetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FleetRepository extends JpaRepository<FleetEntity, Long> {
}
