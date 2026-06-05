package com.company.networkmovers.modules.dispatcher.facade;

import java.util.UUID;

import com.company.networkmovers.modules.dispatcher.dto.request.DispatcherRequest;
import com.company.networkmovers.modules.dispatcher.dto.response.DispatcherResponse;
import com.company.networkmovers.modules.dispatcher.service.DispatcherService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DispatcherFacade {

    private final DispatcherService service;

    public DispatcherFacade(DispatcherService service) {
        this.service = service;
    }

    public DispatcherResponse create(DispatcherRequest request) {
        return service.create(request);
    }

    public DispatcherResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<DispatcherResponse> findAll() {
        return service.findAll();
    }

    public DispatcherResponse update(UUID id, DispatcherRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
