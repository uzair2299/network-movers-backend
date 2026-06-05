package com.company.networkmovers.modules.fleet.facade;

import java.util.UUID;

import com.company.networkmovers.modules.fleet.dto.request.FleetRequest;
import com.company.networkmovers.modules.fleet.dto.response.FleetResponse;
import com.company.networkmovers.modules.fleet.service.FleetService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class FleetFacade {

    private final FleetService service;

    public FleetFacade(FleetService service) {
        this.service = service;
    }

    public FleetResponse create(FleetRequest request) {
        return service.create(request);
    }

    public FleetResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<FleetResponse> findAll() {
        return service.findAll();
    }

    public FleetResponse update(UUID id, FleetRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
