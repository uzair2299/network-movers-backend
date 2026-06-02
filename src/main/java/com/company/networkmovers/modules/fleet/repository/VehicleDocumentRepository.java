package com.company.networkmovers.modules.fleet.repository;

import com.company.networkmovers.modules.fleet.entity.VehicleDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface VehicleDocumentRepository extends JpaRepository<VehicleDocument, UUID> {

    Page<VehicleDocument> findByVehicleId(UUID vehicleId, Pageable pageable);

    @Query("SELECT vd FROM VehicleDocument vd WHERE vd.expiryDate <= :expiryDate AND vd.documentType.expiryRequired = true")
    Page<VehicleDocument> findExpiringDocuments(@Param("expiryDate") LocalDate expiryDate, Pageable pageable);
}
