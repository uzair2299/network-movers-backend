package com.company.networkmovers.modules.logistics.service;

import java.util.UUID;

import com.company.networkmovers.modules.logistics.dto.request.LogisticsRequest;
import com.company.networkmovers.modules.logistics.dto.response.LogisticsResponse;
import java.util.List;

public interface LogisticsService {
    LogisticsResponse create(LogisticsRequest request);
    LogisticsResponse findById(UUID id);
    List<LogisticsResponse> findAll();
    LogisticsResponse update(UUID id, LogisticsRequest request);
    void delete(UUID id);
}
