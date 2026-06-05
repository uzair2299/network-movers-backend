package com.company.networkmovers.modules.route.service;

import java.util.UUID;

import com.company.networkmovers.modules.route.dto.request.RouteRequest;
import com.company.networkmovers.modules.route.dto.response.RouteResponse;
import java.util.List;

public interface RouteService {
    RouteResponse create(RouteRequest request);
    RouteResponse findById(UUID id);
    List<RouteResponse> findAll();
    RouteResponse update(UUID id, RouteRequest request);
    void delete(UUID id);
}
