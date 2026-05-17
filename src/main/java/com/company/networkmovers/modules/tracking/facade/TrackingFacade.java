package com.company.networkmovers.modules.tracking.facade;

import com.company.networkmovers.modules.tracking.dto.request.TrackingRequest;
import com.company.networkmovers.modules.tracking.dto.response.TrackingResponse;
import com.company.networkmovers.modules.tracking.service.TrackingService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TrackingFacade {

    private final TrackingService service;

    public TrackingFacade(TrackingService service) {
        this.service = service;
    }

    public TrackingResponse create(TrackingRequest request) {
        return service.create(request);
    }

    public TrackingResponse findById(Long id) {
        return service.findById(id);
    }

    public List<TrackingResponse> findAll() {
        return service.findAll();
    }

    public TrackingResponse update(Long id, TrackingRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
