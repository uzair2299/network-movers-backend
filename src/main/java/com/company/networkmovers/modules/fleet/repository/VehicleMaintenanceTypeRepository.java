package com.company.networkmovers.modules.fleet.repository;

import com.company.networkmovers.modules.fleet.entity.VehicleMaintenanceType;
import com.company.networkmovers.shared.repository.BaseLookupRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleMaintenanceTypeRepository extends BaseLookupRepository<VehicleMaintenanceType> {
}
