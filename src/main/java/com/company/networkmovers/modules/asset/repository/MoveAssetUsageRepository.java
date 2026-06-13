package com.company.networkmovers.modules.asset.repository;

import com.company.networkmovers.modules.asset.entity.MoveAssetUsage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MoveAssetUsageRepository extends JpaRepository<MoveAssetUsage, UUID> {

    @Query("SELECT mau FROM MoveAssetUsage mau " +
           "LEFT JOIN FETCH mau.items i " +
           "LEFT JOIN FETCH i.asset " +
           "WHERE mau.id = :id AND mau.deleted = false")
    Optional<MoveAssetUsage> findByIdWithDetails(@Param("id") UUID id);

    @Query(value = "SELECT mau FROM MoveAssetUsage mau " +
           "WHERE mau.deleted = false " +
           "AND (:bookingId IS NULL OR mau.bookingId = :bookingId)",
           countQuery = "SELECT count(mau) FROM MoveAssetUsage mau WHERE mau.deleted = false " +
                        "AND (:bookingId IS NULL OR mau.bookingId = :bookingId)")
    Page<MoveAssetUsage> findAllActive(@Param("bookingId") UUID bookingId, Pageable pageable);
}
