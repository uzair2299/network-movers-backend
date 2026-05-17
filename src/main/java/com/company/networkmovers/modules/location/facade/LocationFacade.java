package com.company.networkmovers.modules.location.facade;

import com.company.networkmovers.modules.location.dto.request.LocationRequest;
import com.company.networkmovers.modules.location.dto.response.LocationResponse;
import com.company.networkmovers.modules.location.service.LocationService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class LocationFacade {

    private final LocationService service;

    public LocationFacade(LocationService service) {
        this.service = service;
    }

    public LocationResponse create(LocationRequest request) {
        return service.create(request);
    }

    public LocationResponse findById(Long id) {
        return service.findById(id);
    }

    public List<LocationResponse> findAll() {
        return service.findAll();
    }

    public LocationResponse update(Long id, LocationRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
