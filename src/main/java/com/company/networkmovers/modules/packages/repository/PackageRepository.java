package com.company.networkmovers.modules.packages.repository;

import java.util.UUID;

import com.company.networkmovers.modules.packages.entity.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity, UUID> {
}
