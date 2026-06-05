package com.company.networkmovers.modules.analytics.service;

import java.util.UUID;

import com.company.networkmovers.modules.analytics.dto.request.AnalyticsRequest;
import com.company.networkmovers.modules.analytics.dto.response.AnalyticsResponse;
import java.util.List;

public interface AnalyticsService {
    AnalyticsResponse create(AnalyticsRequest request);
    AnalyticsResponse findById(UUID id);
    List<AnalyticsResponse> findAll();
    AnalyticsResponse update(UUID id, AnalyticsRequest request);
    void delete(UUID id);
}
