package com.company.networkmovers.modules.route.facade;

import java.util.UUID;

import com.company.networkmovers.modules.route.dto.request.RouteRequest;
import com.company.networkmovers.modules.route.dto.response.RouteResponse;
import com.company.networkmovers.modules.route.service.RouteService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RouteFacade {

    private final RouteService service;

    public RouteFacade(RouteService service) {
        this.service = service;
    }

    public RouteResponse create(RouteRequest request) {
        return service.create(request);
    }

    public RouteResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<RouteResponse> findAll() {
        return service.findAll();
    }

    public RouteResponse update(UUID id, RouteRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
