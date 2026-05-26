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

    @Query("SELECT t FROM PropertyType t WHERE t.category.id = :categoryId")
    Page<PropertyType> findByCategoryId(@Param("categoryId") UUID categoryId, Pageable pageable);

    @Query("SELECT t FROM PropertyType t " +
           "WHERE t.category.id = :categoryId " +
           "AND (LOWER(t.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "     LOWER(t.code) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<PropertyType> findByCategoryIdAndSearch(
            @Param("categoryId") UUID categoryId,
            @Param("search") String search,
            Pageable pageable);
}
