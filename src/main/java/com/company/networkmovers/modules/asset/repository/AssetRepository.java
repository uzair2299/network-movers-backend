package com.company.networkmovers.modules.asset.repository;

import com.company.networkmovers.modules.asset.entity.Asset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssetRepository extends JpaRepository<Asset, UUID> {

    @Query("SELECT a FROM Asset a " +
           "JOIN FETCH a.assetType " +
           "JOIN FETCH a.category " +
           "JOIN FETCH a.company " +
           "JOIN FETCH a.unitOfMeasure " +
           "LEFT JOIN FETCH a.supplier " +
           "WHERE a.id = :id AND a.deleted = false")
    Optional<Asset> findByIdWithDetails(@Param("id") UUID id);

    @Query(value = "SELECT a FROM Asset a " +
           "JOIN FETCH a.assetType " +
           "JOIN FETCH a.category " +
           "JOIN FETCH a.company " +
           "JOIN FETCH a.unitOfMeasure " +
           "LEFT JOIN FETCH a.supplier " +
           "WHERE a.deleted = false " +
           "AND (:search IS NULL OR LOWER(a.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(a.code) LIKE LOWER(CONCAT('%', :search, '%')))",
           countQuery = "SELECT count(a) FROM Asset a WHERE a.deleted = false " +
                        "AND (:search IS NULL OR LOWER(a.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(a.code) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Asset> findAllActive(@Param("search") String search, Pageable pageable);

    Optional<Asset> findByCodeAndDeletedFalse(String code);
    boolean existsByCodeAndDeletedFalse(String code);
    boolean existsByCodeAndIdNotAndDeletedFalse(String code, UUID id);
}
