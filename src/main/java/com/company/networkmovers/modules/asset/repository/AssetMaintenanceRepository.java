package com.company.networkmovers.modules.asset.repository;

import com.company.networkmovers.modules.asset.entity.AssetMaintenance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssetMaintenanceRepository extends JpaRepository<AssetMaintenance, UUID> {

    @Query("SELECT am FROM AssetMaintenance am " +
           "JOIN FETCH am.asset " +
           "WHERE am.id = :id AND am.deleted = false")
    Optional<AssetMaintenance> findByIdWithDetails(@Param("id") UUID id);

    @Query(value = "SELECT am FROM AssetMaintenance am " +
           "JOIN FETCH am.asset " +
           "WHERE am.deleted = false " +
           "AND (:assetId IS NULL OR am.asset.id = :assetId)",
           countQuery = "SELECT count(am) FROM AssetMaintenance am WHERE am.deleted = false " +
                        "AND (:assetId IS NULL OR am.asset.id = :assetId)")
    Page<AssetMaintenance> findAllActive(@Param("assetId") UUID assetId, Pageable pageable);
}
