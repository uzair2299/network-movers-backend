package com.company.networkmovers.modules.dispatch.facade;

import com.company.networkmovers.modules.dispatch.dto.request.DispatchRequest;
import com.company.networkmovers.modules.dispatch.dto.response.DispatchResponse;
import com.company.networkmovers.modules.dispatch.service.DispatchService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DispatchFacade {

    private final DispatchService service;

    public DispatchFacade(DispatchService service) {
        this.service = service;
    }

    public DispatchResponse create(DispatchRequest request) {
        return service.create(request);
    }

    public DispatchResponse findById(Long id) {
        return service.findById(id);
    }

    public List<DispatchResponse> findAll() {
        return service.findAll();
    }

    public DispatchResponse update(Long id, DispatchRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
