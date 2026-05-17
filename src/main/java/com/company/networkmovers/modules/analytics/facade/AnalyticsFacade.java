package com.company.networkmovers.modules.analytics.facade;

import com.company.networkmovers.modules.analytics.dto.request.AnalyticsRequest;
import com.company.networkmovers.modules.analytics.dto.response.AnalyticsResponse;
import com.company.networkmovers.modules.analytics.service.AnalyticsService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AnalyticsFacade {

    private final AnalyticsService service;

    public AnalyticsFacade(AnalyticsService service) {
        this.service = service;
    }

    public AnalyticsResponse create(AnalyticsRequest request) {
        return service.create(request);
    }

    public AnalyticsResponse findById(Long id) {
        return service.findById(id);
    }

    public List<AnalyticsResponse> findAll() {
        return service.findAll();
    }

    public AnalyticsResponse update(Long id, AnalyticsRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
