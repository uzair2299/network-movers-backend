package com.company.networkmovers.modules.fleet.repository;

import com.company.networkmovers.modules.fleet.entity.VehicleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel, UUID> {

    @Query("SELECT vm FROM VehicleModel vm WHERE " +
           "LOWER(vm.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(vm.code) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(vm.make.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<VehicleModel> findBySearch(@Param("search") String search, Pageable pageable);

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, UUID id);
}
