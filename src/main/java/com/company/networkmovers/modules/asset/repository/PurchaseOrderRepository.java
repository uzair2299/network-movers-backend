package com.company.networkmovers.modules.asset.repository;

import com.company.networkmovers.modules.asset.entity.PurchaseOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, UUID> {

    @Query("SELECT po FROM PurchaseOrder po " +
           "JOIN FETCH po.supplier " +
           "LEFT JOIN FETCH po.items i " +
           "LEFT JOIN FETCH i.asset " +
           "WHERE po.id = :id AND po.deleted = false")
    Optional<PurchaseOrder> findByIdWithDetails(@Param("id") UUID id);

    @Query(value = "SELECT po FROM PurchaseOrder po " +
           "JOIN FETCH po.supplier " +
           "WHERE po.deleted = false",
           countQuery = "SELECT count(po) FROM PurchaseOrder po WHERE po.deleted = false")
    Page<PurchaseOrder> findAllActive(Pageable pageable);

    boolean existsByPurchaseOrderNumberAndDeletedFalse(String purchaseOrderNumber);
    boolean existsByPurchaseOrderNumberAndIdNotAndDeletedFalse(String purchaseOrderNumber, UUID id);
}
