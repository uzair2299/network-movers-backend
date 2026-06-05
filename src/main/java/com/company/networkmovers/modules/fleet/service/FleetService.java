package com.company.networkmovers.modules.fleet.service;

import java.util.UUID;

import com.company.networkmovers.modules.fleet.dto.request.FleetRequest;
import com.company.networkmovers.modules.fleet.dto.response.FleetResponse;
import java.util.List;

public interface FleetService {
    FleetResponse create(FleetRequest request);
    FleetResponse findById(UUID id);
    List<FleetResponse> findAll();
    FleetResponse update(UUID id, FleetRequest request);
    void delete(UUID id);
}
