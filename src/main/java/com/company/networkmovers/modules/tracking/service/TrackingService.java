package com.company.networkmovers.modules.tracking.service;

import com.company.networkmovers.modules.tracking.dto.request.TrackingRequest;
import com.company.networkmovers.modules.tracking.dto.response.TrackingResponse;
import java.util.List;

public interface TrackingService {
    TrackingResponse create(TrackingRequest request);
    TrackingResponse findById(Long id);
    List<TrackingResponse> findAll();
    TrackingResponse update(Long id, TrackingRequest request);
    void delete(Long id);
}
