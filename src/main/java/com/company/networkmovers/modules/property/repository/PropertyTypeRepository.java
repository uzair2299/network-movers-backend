package com.company.networkmovers.modules.property.repository;

import com.company.networkmovers.modules.property.entity.PropertyType;
import com.company.networkmovers.shared.repository.BaseLookupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PropertyTypeRepository extends BaseLookupRepository<PropertyType> {

    @Query("SELECT t FROM PropertyType t " +
           "WHERE (:categoryId IS NULL OR t.category.id = :categoryId) " +
           "AND (:search IS NULL OR " +
           "     LOWER(t.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "     LOWER(t.code) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<PropertyType> searchPageWithCategory(
            @Param("search") String search,
            @Param("categoryId") UUID categoryId,
            Pageable pageable);
}
