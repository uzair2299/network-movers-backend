package com.company.networkmovers.modules.asset.repository;

import com.company.networkmovers.modules.asset.entity.EmployeeAssetAssignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeAssetAssignmentRepository extends JpaRepository<EmployeeAssetAssignment, UUID> {

    @Query("SELECT eaa FROM EmployeeAssetAssignment eaa " +
           "JOIN FETCH eaa.asset " +
           "WHERE eaa.id = :id AND eaa.deleted = false")
    Optional<EmployeeAssetAssignment> findByIdWithDetails(@Param("id") UUID id);

    @Query(value = "SELECT eaa FROM EmployeeAssetAssignment eaa " +
           "JOIN FETCH eaa.asset " +
           "WHERE eaa.deleted = false " +
           "AND (:employeeId IS NULL OR eaa.employeeId = :employeeId)",
           countQuery = "SELECT count(eaa) FROM EmployeeAssetAssignment eaa WHERE eaa.deleted = false " +
                        "AND (:employeeId IS NULL OR eaa.employeeId = :employeeId)")
    Page<EmployeeAssetAssignment> findAllActive(@Param("employeeId") UUID employeeId, Pageable pageable);
}
