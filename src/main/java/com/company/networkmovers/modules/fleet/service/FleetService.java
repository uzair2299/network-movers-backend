package com.company.networkmovers.modules.fleet.service;

import com.company.networkmovers.modules.fleet.dto.request.FleetRequest;
import com.company.networkmovers.modules.fleet.dto.response.FleetResponse;
import java.util.List;

public interface FleetService {
    FleetResponse create(FleetRequest request);
    FleetResponse findById(Long id);
    List<FleetResponse> findAll();
    FleetResponse update(Long id, FleetRequest request);
    void delete(Long id);
}
