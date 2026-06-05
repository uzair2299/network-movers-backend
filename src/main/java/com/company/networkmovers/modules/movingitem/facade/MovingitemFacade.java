package com.company.networkmovers.modules.movingitem.facade;

import java.util.UUID;

import com.company.networkmovers.modules.movingitem.dto.request.MovingitemRequest;
import com.company.networkmovers.modules.movingitem.dto.response.MovingitemResponse;
import com.company.networkmovers.modules.movingitem.service.MovingitemService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MovingitemFacade {

    private final MovingitemService service;

    public MovingitemFacade(MovingitemService service) {
        this.service = service;
    }

    public MovingitemResponse create(MovingitemRequest request) {
        return service.create(request);
    }

    public MovingitemResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<MovingitemResponse> findAll() {
        return service.findAll();
    }

    public MovingitemResponse update(UUID id, MovingitemRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
