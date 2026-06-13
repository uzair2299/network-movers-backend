package com.company.networkmovers.modules.property.repository;

import com.company.networkmovers.modules.property.entity.MoveStatusLayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MoveStatusLayoutRepository extends JpaRepository<MoveStatusLayout, UUID> {

    @Query("SELECT l FROM MoveStatusLayout l WHERE l.status.id = :statusId AND l.deleted = false")
    Optional<MoveStatusLayout> findActiveByStatusId(@Param("statusId") UUID statusId);
}
