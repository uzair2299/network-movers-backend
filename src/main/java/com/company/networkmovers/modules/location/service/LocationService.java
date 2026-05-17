package com.company.networkmovers.modules.location.service;

import com.company.networkmovers.modules.location.dto.request.LocationRequest;
import com.company.networkmovers.modules.location.dto.response.LocationResponse;
import java.util.List;

public interface LocationService {
    LocationResponse create(LocationRequest request);
    LocationResponse findById(Long id);
    List<LocationResponse> findAll();
    LocationResponse update(Long id, LocationRequest request);
    void delete(Long id);
}
