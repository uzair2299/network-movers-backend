package com.company.networkmovers.modules.asset.repository;

import com.company.networkmovers.modules.asset.entity.AssetImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AssetImageRepository extends JpaRepository<AssetImage, UUID> {
    @Query("SELECT i FROM AssetImage i WHERE i.asset.id = :assetId AND i.deleted = false")
    List<AssetImage> findByAssetId(@Param("assetId") UUID assetId);
}
