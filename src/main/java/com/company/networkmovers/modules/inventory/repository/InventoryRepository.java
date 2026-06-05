package com.company.networkmovers.modules.inventory.repository;

import java.util.UUID;

import com.company.networkmovers.modules.inventory.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, UUID> {
}
