package com.company.networkmovers.modules.fleet.repository;

import com.company.networkmovers.modules.fleet.entity.Vehicle;
import com.company.networkmovers.modules.fleet.entity.enums.VehicleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("fleetVehicleRepository")
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    @Query("SELECT v FROM Vehicle v WHERE " +
           "LOWER(v.vehicleCode) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(v.registrationNo) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(v.vehicleModel.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Vehicle> findBySearch(@Param("search") String search, Pageable pageable);

    @Query("SELECT v FROM Vehicle v WHERE v.status = :status")
    Page<Vehicle> findByStatus(@Param("status") VehicleStatus status, Pageable pageable);

    boolean existsByVehicleCode(String vehicleCode);
    boolean existsByVehicleCodeAndIdNot(String vehicleCode, UUID id);

    boolean existsByRegistrationNo(String registrationNo);
    boolean existsByRegistrationNoAndIdNot(String registrationNo, UUID id);
}
