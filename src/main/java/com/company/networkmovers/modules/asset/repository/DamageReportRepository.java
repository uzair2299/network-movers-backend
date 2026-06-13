package com.company.networkmovers.modules.asset.repository;

import com.company.networkmovers.modules.asset.entity.DamageReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DamageReportRepository extends JpaRepository<DamageReport, UUID> {

    @Query("SELECT dr FROM DamageReport dr " +
           "JOIN FETCH dr.asset " +
           "WHERE dr.id = :id AND dr.deleted = false")
    Optional<DamageReport> findByIdWithDetails(@Param("id") UUID id);

    @Query(value = "SELECT dr FROM DamageReport dr " +
           "JOIN FETCH dr.asset " +
           "WHERE dr.deleted = false " +
           "AND (:assetId IS NULL OR dr.asset.id = :assetId)",
           countQuery = "SELECT count(dr) FROM DamageReport dr WHERE dr.deleted = false " +
                        "AND (:assetId IS NULL OR dr.asset.id = :assetId)")
    Page<DamageReport> findAllActive(@Param("assetId") UUID assetId, Pageable pageable);
}
