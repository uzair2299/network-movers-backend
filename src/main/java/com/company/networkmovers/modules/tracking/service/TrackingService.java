package com.company.networkmovers.modules.tracking.service;

import java.util.UUID;

import com.company.networkmovers.modules.tracking.dto.request.TrackingRequest;
import com.company.networkmovers.modules.tracking.dto.response.TrackingResponse;
import java.util.List;

public interface TrackingService {
    TrackingResponse create(TrackingRequest request);
    TrackingResponse findById(UUID id);
    List<TrackingResponse> findAll();
    TrackingResponse update(UUID id, TrackingRequest request);
    void delete(UUID id);
}
