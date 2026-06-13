package com.company.networkmovers.modules.property.repository;

import com.company.networkmovers.modules.property.entity.MoveStatusTransition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MoveStatusTransitionRepository extends JpaRepository<MoveStatusTransition, UUID> {

    @Query("SELECT t FROM MoveStatusTransition t " +
           "JOIN FETCH t.fromStatus fs " +
           "JOIN FETCH t.toStatus ts " +
           "LEFT JOIN FETCH t.allowedRole r " +
           "WHERE t.deleted = false")
    List<MoveStatusTransition> findAllActiveTransitions();
}
