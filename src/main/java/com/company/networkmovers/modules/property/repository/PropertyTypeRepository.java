package com.company.networkmovers.modules.property.repository;

import com.company.networkmovers.modules.property.entity.PropertyType;
import com.company.networkmovers.shared.repository.BaseLookupRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyTypeRepository extends BaseLookupRepository<PropertyType> {
    
    @org.springframework.data.jpa.repository.Query("SELECT pt FROM PropertyType pt WHERE pt.category.id = :categoryId AND pt.active = true AND pt.category.active = true")
    java.util.List<PropertyType> findByCategoryIdAndActiveTrue(@org.springframework.data.repository.query.Param("categoryId") java.util.UUID categoryId);
}
