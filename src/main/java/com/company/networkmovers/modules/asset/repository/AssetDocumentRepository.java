package com.company.networkmovers.modules.asset.repository;

import com.company.networkmovers.modules.asset.entity.AssetDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AssetDocumentRepository extends JpaRepository<AssetDocument, UUID> {
    @Query("SELECT d FROM AssetDocument d WHERE d.asset.id = :assetId AND d.deleted = false")
    List<AssetDocument> findByAssetId(@Param("assetId") UUID assetId);
}
