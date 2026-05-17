package com.company.networkmovers.modules.mover.facade;

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

    public MoverResponse findById(Long id) {
        return service.findById(id);
    }

    public List<MoverResponse> findAll() {
        return service.findAll();
    }

    public MoverResponse update(Long id, MoverRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
