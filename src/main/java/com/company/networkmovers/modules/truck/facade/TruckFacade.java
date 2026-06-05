package com.company.networkmovers.modules.truck.facade;

import java.util.UUID;

import com.company.networkmovers.modules.truck.dto.request.TruckRequest;
import com.company.networkmovers.modules.truck.dto.response.TruckResponse;
import com.company.networkmovers.modules.truck.service.TruckService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TruckFacade {

    private final TruckService service;

    public TruckFacade(TruckService service) {
        this.service = service;
    }

    public TruckResponse create(TruckRequest request) {
        return service.create(request);
    }

    public TruckResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<TruckResponse> findAll() {
        return service.findAll();
    }

    public TruckResponse update(UUID id, TruckRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
