package com.company.networkmovers.modules.asset.repository;

import com.company.networkmovers.modules.asset.entity.StockAudit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockAuditRepository extends JpaRepository<StockAudit, UUID> {

    @Query("SELECT sa FROM StockAudit sa " +
           "LEFT JOIN FETCH sa.items i " +
           "LEFT JOIN FETCH i.asset " +
           "LEFT JOIN FETCH i.location " +
           "WHERE sa.id = :id AND sa.deleted = false")
    Optional<StockAudit> findByIdWithDetails(@Param("id") UUID id);

    @Query(value = "SELECT sa FROM StockAudit sa " +
           "WHERE sa.deleted = false",
           countQuery = "SELECT count(sa) FROM StockAudit sa WHERE sa.deleted = false")
    Page<StockAudit> findAllActive(Pageable pageable);
}
