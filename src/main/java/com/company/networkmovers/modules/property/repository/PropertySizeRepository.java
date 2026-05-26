package com.company.networkmovers.modules.property.repository;

import com.company.networkmovers.modules.property.entity.PropertySize;
import com.company.networkmovers.shared.repository.BaseLookupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PropertySizeRepository extends BaseLookupRepository<PropertySize> {

    @Query("SELECT s FROM PropertySize s " +
           "WHERE (:typeId IS NULL OR s.type.id = :typeId) " +
           "AND (:search IS NULL OR " +
           "     LOWER(s.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "     LOWER(s.code) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<PropertySize> searchPageWithType(
            @Param("search") String search,
            @Param("typeId") UUID typeId,
            Pageable pageable);
}
