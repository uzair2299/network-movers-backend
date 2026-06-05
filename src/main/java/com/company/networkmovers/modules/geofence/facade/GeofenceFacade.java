package com.company.networkmovers.modules.geofence.facade;

import java.util.UUID;

import com.company.networkmovers.modules.geofence.dto.request.GeofenceRequest;
import com.company.networkmovers.modules.geofence.dto.response.GeofenceResponse;
import com.company.networkmovers.modules.geofence.service.GeofenceService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GeofenceFacade {

    private final GeofenceService service;

    public GeofenceFacade(GeofenceService service) {
        this.service = service;
    }

    public GeofenceResponse create(GeofenceRequest request) {
        return service.create(request);
    }

    public GeofenceResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<GeofenceResponse> findAll() {
        return service.findAll();
    }

    public GeofenceResponse update(UUID id, GeofenceRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
