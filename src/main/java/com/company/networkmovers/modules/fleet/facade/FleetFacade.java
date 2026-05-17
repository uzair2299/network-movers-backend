package com.company.networkmovers.modules.fleet.facade;

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

    public FleetResponse findById(Long id) {
        return service.findById(id);
    }

    public List<FleetResponse> findAll() {
        return service.findAll();
    }

    public FleetResponse update(Long id, FleetRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
