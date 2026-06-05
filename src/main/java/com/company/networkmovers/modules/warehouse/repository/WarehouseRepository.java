package com.company.networkmovers.modules.warehouse.repository;

import java.util.UUID;

import com.company.networkmovers.modules.warehouse.entity.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<WarehouseEntity, UUID> {
}
