package com.company.networkmovers.modules.asset.repository;

import com.company.networkmovers.modules.asset.entity.AssetStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssetStockRepository extends JpaRepository<AssetStock, UUID> {

    @Query("SELECT s FROM AssetStock s " +
           "JOIN FETCH s.asset " +
           "JOIN FETCH s.location " +
           "WHERE s.id = :id AND s.deleted = false")
    Optional<AssetStock> findByIdWithDetails(@Param("id") UUID id);

    @Query("SELECT s FROM AssetStock s " +
           "JOIN FETCH s.asset " +
           "JOIN FETCH s.location " +
           "WHERE s.asset.id = :assetId AND s.location.id = :locationId AND s.deleted = false")
    Optional<AssetStock> findByAssetIdAndLocationId(@Param("assetId") UUID assetId, @Param("locationId") UUID locationId);

    @Query(value = "SELECT s FROM AssetStock s " +
           "JOIN FETCH s.asset " +
           "JOIN FETCH s.location " +
           "WHERE s.deleted = false " +
           "AND (:locationId IS NULL OR s.location.id = :locationId)",
           countQuery = "SELECT count(s) FROM AssetStock s WHERE s.deleted = false " +
                        "AND (:locationId IS NULL OR s.location.id = :locationId)")
    Page<AssetStock> findAllActive(@Param("locationId") UUID locationId, Pageable pageable);
}
