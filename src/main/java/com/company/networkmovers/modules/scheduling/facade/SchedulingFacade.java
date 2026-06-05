package com.company.networkmovers.modules.scheduling.facade;

import java.util.UUID;

import com.company.networkmovers.modules.scheduling.dto.request.SchedulingRequest;
import com.company.networkmovers.modules.scheduling.dto.response.SchedulingResponse;
import com.company.networkmovers.modules.scheduling.service.SchedulingService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SchedulingFacade {

    private final SchedulingService service;

    public SchedulingFacade(SchedulingService service) {
        this.service = service;
    }

    public SchedulingResponse create(SchedulingRequest request) {
        return service.create(request);
    }

    public SchedulingResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<SchedulingResponse> findAll() {
        return service.findAll();
    }

    public SchedulingResponse update(UUID id, SchedulingRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
