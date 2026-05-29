package com.company.networkmovers.modules.property.repository;

import com.company.networkmovers.modules.property.entity.MoveStatus;
import com.company.networkmovers.shared.repository.BaseLookupRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveStatusRepository extends BaseLookupRepository<MoveStatus> {
}
