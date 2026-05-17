package com.company.networkmovers.modules.route.facade;

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

    public RouteResponse findById(Long id) {
        return service.findById(id);
    }

    public List<RouteResponse> findAll() {
        return service.findAll();
    }

    public RouteResponse update(Long id, RouteRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
