package com.company.networkmovers.modules.asset.repository;

import com.company.networkmovers.modules.asset.entity.MoveAssetUsageItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MoveAssetUsageItemRepository extends JpaRepository<MoveAssetUsageItem, UUID> {
}
