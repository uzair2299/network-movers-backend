package com.company.networkmovers.modules.fleet.repository;

import com.company.networkmovers.modules.fleet.entity.VehicleFuelLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleFuelLogRepository extends JpaRepository<VehicleFuelLog, UUID> {

    Page<VehicleFuelLog> findByVehicleId(UUID vehicleId, Pageable pageable);
}
