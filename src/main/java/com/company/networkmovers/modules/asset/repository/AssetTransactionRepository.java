package com.company.networkmovers.modules.asset.repository;

import com.company.networkmovers.modules.asset.entity.AssetTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssetTransactionRepository extends JpaRepository<AssetTransaction, UUID> {

    @Query(value = "SELECT t FROM AssetTransaction t " +
           "JOIN FETCH t.asset " +
           "LEFT JOIN FETCH t.sourceLocation " +
           "LEFT JOIN FETCH t.destinationLocation " +
           "WHERE t.asset.id = :assetId AND t.deleted = false",
           countQuery = "SELECT count(t) FROM AssetTransaction t WHERE t.asset.id = :assetId AND t.deleted = false")
    Page<AssetTransaction> findByAssetId(@Param("assetId") UUID assetId, Pageable pageable);
}
