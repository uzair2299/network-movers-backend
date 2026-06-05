package com.company.networkmovers.modules.logistics.facade;

import java.util.UUID;

import com.company.networkmovers.modules.logistics.dto.request.LogisticsRequest;
import com.company.networkmovers.modules.logistics.dto.response.LogisticsResponse;
import com.company.networkmovers.modules.logistics.service.LogisticsService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class LogisticsFacade {

    private final LogisticsService service;

    public LogisticsFacade(LogisticsService service) {
        this.service = service;
    }

    public LogisticsResponse create(LogisticsRequest request) {
        return service.create(request);
    }

    public LogisticsResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<LogisticsResponse> findAll() {
        return service.findAll();
    }

    public LogisticsResponse update(UUID id, LogisticsRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
