package com.company.networkmovers.modules.fleet.repository;

import com.company.networkmovers.modules.fleet.entity.VehicleMaintenance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface VehicleMaintenanceRepository extends JpaRepository<VehicleMaintenance, UUID> {

    Page<VehicleMaintenance> findByVehicleId(UUID vehicleId, Pageable pageable);

    @Query("SELECT vm FROM VehicleMaintenance vm WHERE vm.nextServiceDate <= :targetDate OR vm.nextServiceKm <= :targetKm")
    Page<VehicleMaintenance> findUpcomingMaintenance(@Param("targetDate") LocalDate targetDate, 
                                                     @Param("targetKm") BigDecimal targetKm, 
                                                     Pageable pageable);
}
