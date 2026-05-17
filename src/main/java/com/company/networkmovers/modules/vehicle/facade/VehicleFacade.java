package com.company.networkmovers.modules.vehicle.facade;

import com.company.networkmovers.modules.vehicle.dto.request.VehicleRequest;
import com.company.networkmovers.modules.vehicle.dto.response.VehicleResponse;
import com.company.networkmovers.modules.vehicle.service.VehicleService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class VehicleFacade {

    private final VehicleService service;

    public VehicleFacade(VehicleService service) {
        this.service = service;
    }

    public VehicleResponse create(VehicleRequest request) {
        return service.create(request);
    }

    public VehicleResponse findById(Long id) {
        return service.findById(id);
    }

    public List<VehicleResponse> findAll() {
        return service.findAll();
    }

    public VehicleResponse update(Long id, VehicleRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
