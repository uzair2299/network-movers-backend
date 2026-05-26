package com.company.networkmovers.modules.property.repository;

import com.company.networkmovers.modules.property.entity.PropertyCategory;
import com.company.networkmovers.shared.repository.BaseLookupRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyCategoryRepository extends BaseLookupRepository<PropertyCategory> {
}
