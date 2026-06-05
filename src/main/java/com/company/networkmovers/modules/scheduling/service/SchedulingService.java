package com.company.networkmovers.modules.scheduling.service;

import java.util.UUID;

import com.company.networkmovers.modules.scheduling.dto.request.SchedulingRequest;
import com.company.networkmovers.modules.scheduling.dto.response.SchedulingResponse;
import java.util.List;

public interface SchedulingService {
    SchedulingResponse create(SchedulingRequest request);
    SchedulingResponse findById(UUID id);
    List<SchedulingResponse> findAll();
    SchedulingResponse update(UUID id, SchedulingRequest request);
    void delete(UUID id);
}
