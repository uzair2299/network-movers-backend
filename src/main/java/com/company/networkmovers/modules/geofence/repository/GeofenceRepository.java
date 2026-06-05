package com.company.networkmovers.modules.geofence.repository;

import java.util.UUID;

import com.company.networkmovers.modules.geofence.entity.GeofenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeofenceRepository extends JpaRepository<GeofenceEntity, UUID> {
}
