package com.company.networkmovers.modules.trip.repository;

import java.util.UUID;

import com.company.networkmovers.modules.trip.entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<TripEntity, UUID> {
}
