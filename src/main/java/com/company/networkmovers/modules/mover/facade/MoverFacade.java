package com.company.networkmovers.modules.mover.facade;

import java.util.UUID;

import com.company.networkmovers.modules.mover.dto.request.MoverRequest;
import com.company.networkmovers.modules.mover.dto.response.MoverResponse;
import com.company.networkmovers.modules.mover.service.MoverService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MoverFacade {

    private final MoverService service;

    public MoverFacade(MoverService service) {
        this.service = service;
    }

    public MoverResponse create(MoverRequest request) {
        return service.create(request);
    }

    public MoverResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<MoverResponse> findAll() {
        return service.findAll();
    }

    public MoverResponse update(UUID id, MoverRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
