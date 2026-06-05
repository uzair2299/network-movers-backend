package com.company.networkmovers.modules.trip.facade;

import java.util.UUID;

import com.company.networkmovers.modules.trip.dto.request.TripRequest;
import com.company.networkmovers.modules.trip.dto.response.TripResponse;
import com.company.networkmovers.modules.trip.service.TripService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TripFacade {

    private final TripService service;

    public TripFacade(TripService service) {
        this.service = service;
    }

    public TripResponse create(TripRequest request) {
        return service.create(request);
    }

    public TripResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<TripResponse> findAll() {
        return service.findAll();
    }

    public TripResponse update(UUID id, TripRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
