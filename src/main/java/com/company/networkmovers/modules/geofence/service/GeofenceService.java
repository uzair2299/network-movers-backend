package com.company.networkmovers.modules.geofence.service;

import java.util.UUID;

import com.company.networkmovers.modules.geofence.dto.request.GeofenceRequest;
import com.company.networkmovers.modules.geofence.dto.response.GeofenceResponse;
import java.util.List;

public interface GeofenceService {
    GeofenceResponse create(GeofenceRequest request);
    GeofenceResponse findById(UUID id);
    List<GeofenceResponse> findAll();
    GeofenceResponse update(UUID id, GeofenceRequest request);
    void delete(UUID id);
}
