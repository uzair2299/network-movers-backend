package com.company.networkmovers.modules.geofence.service;

import com.company.networkmovers.modules.geofence.dto.request.GeofenceRequest;
import com.company.networkmovers.modules.geofence.dto.response.GeofenceResponse;
import java.util.List;

public interface GeofenceService {
    GeofenceResponse create(GeofenceRequest request);
    GeofenceResponse findById(Long id);
    List<GeofenceResponse> findAll();
    GeofenceResponse update(Long id, GeofenceRequest request);
    void delete(Long id);
}
