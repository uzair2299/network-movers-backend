package com.company.networkmovers.modules.scheduling.service;

import com.company.networkmovers.modules.scheduling.dto.request.SchedulingRequest;
import com.company.networkmovers.modules.scheduling.dto.response.SchedulingResponse;
import java.util.List;

public interface SchedulingService {
    SchedulingResponse create(SchedulingRequest request);
    SchedulingResponse findById(Long id);
    List<SchedulingResponse> findAll();
    SchedulingResponse update(Long id, SchedulingRequest request);
    void delete(Long id);
}
