package com.company.networkmovers.modules.logistics.service;

import com.company.networkmovers.modules.logistics.dto.request.LogisticsRequest;
import com.company.networkmovers.modules.logistics.dto.response.LogisticsResponse;
import java.util.List;

public interface LogisticsService {
    LogisticsResponse create(LogisticsRequest request);
    LogisticsResponse findById(Long id);
    List<LogisticsResponse> findAll();
    LogisticsResponse update(Long id, LogisticsRequest request);
    void delete(Long id);
}
