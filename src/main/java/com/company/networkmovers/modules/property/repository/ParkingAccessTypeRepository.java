package com.company.networkmovers.modules.property.repository;

import com.company.networkmovers.modules.property.entity.ParkingAccessType;
import com.company.networkmovers.shared.repository.BaseLookupRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingAccessTypeRepository extends BaseLookupRepository<ParkingAccessType> {
}
