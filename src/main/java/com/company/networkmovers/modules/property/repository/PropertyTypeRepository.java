package com.company.networkmovers.modules.property.repository;

import com.company.networkmovers.modules.property.entity.PropertyType;
import com.company.networkmovers.shared.repository.BaseLookupRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyTypeRepository extends BaseLookupRepository<PropertyType> {
}
