package com.company.networkmovers.modules.analytics.service;

import com.company.networkmovers.modules.analytics.dto.request.AnalyticsRequest;
import com.company.networkmovers.modules.analytics.dto.response.AnalyticsResponse;
import java.util.List;

public interface AnalyticsService {
    AnalyticsResponse create(AnalyticsRequest request);
    AnalyticsResponse findById(Long id);
    List<AnalyticsResponse> findAll();
    AnalyticsResponse update(Long id, AnalyticsRequest request);
    void delete(Long id);
}
