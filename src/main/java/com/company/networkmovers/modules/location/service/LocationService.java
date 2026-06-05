package com.company.networkmovers.modules.location.service;

import java.util.UUID;

import com.company.networkmovers.modules.location.dto.request.LocationRequest;
import com.company.networkmovers.modules.location.dto.response.LocationResponse;
import java.util.List;

public interface LocationService {
    LocationResponse create(LocationRequest request);
    LocationResponse findById(UUID id);
    List<LocationResponse> findAll();
    LocationResponse update(UUID id, LocationRequest request);
    void delete(UUID id);
}
