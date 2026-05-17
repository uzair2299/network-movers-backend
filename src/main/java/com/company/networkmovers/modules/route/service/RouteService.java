package com.company.networkmovers.modules.route.service;

import com.company.networkmovers.modules.route.dto.request.RouteRequest;
import com.company.networkmovers.modules.route.dto.response.RouteResponse;
import java.util.List;

public interface RouteService {
    RouteResponse create(RouteRequest request);
    RouteResponse findById(Long id);
    List<RouteResponse> findAll();
    RouteResponse update(Long id, RouteRequest request);
    void delete(Long id);
}
