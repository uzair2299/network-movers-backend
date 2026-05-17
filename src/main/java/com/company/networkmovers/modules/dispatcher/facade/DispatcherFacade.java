package com.company.networkmovers.modules.dispatcher.facade;

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

    public DispatcherResponse findById(Long id) {
        return service.findById(id);
    }

    public List<DispatcherResponse> findAll() {
        return service.findAll();
    }

    public DispatcherResponse update(Long id, DispatcherRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
