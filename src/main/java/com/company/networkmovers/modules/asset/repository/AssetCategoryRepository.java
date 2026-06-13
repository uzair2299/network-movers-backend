package com.company.networkmovers.modules.asset.repository;

import com.company.networkmovers.modules.asset.entity.AssetCategory;
import com.company.networkmovers.shared.repository.BaseLookupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssetCategoryRepository extends BaseLookupRepository<AssetCategory> {

    @Query("SELECT ac FROM AssetCategory ac " +
           "JOIN FETCH ac.assetType " +
           "LEFT JOIN FETCH ac.parentCategory " +
           "WHERE ac.id = :id AND ac.deleted = false")
    Optional<AssetCategory> findByIdWithDetails(@Param("id") UUID id);

    @Query(value = "SELECT ac FROM AssetCategory ac " +
           "JOIN FETCH ac.assetType " +
           "LEFT JOIN FETCH ac.parentCategory " +
           "WHERE ac.deleted = false",
           countQuery = "SELECT count(ac) FROM AssetCategory ac WHERE ac.deleted = false")
    Page<AssetCategory> findAllActive(Pageable pageable);
}
