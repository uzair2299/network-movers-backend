package com.company.networkmovers.modules.vendor.repository;

import java.util.UUID;

import com.company.networkmovers.modules.vendor.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity, UUID> {
}
