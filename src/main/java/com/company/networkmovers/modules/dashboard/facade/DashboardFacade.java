package com.company.networkmovers.modules.dashboard.facade;

import com.company.networkmovers.modules.dashboard.dto.request.DashboardRequest;
import com.company.networkmovers.modules.dashboard.dto.response.DashboardResponse;
import com.company.networkmovers.modules.dashboard.service.DashboardService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DashboardFacade {

    private final DashboardService service;

    public DashboardFacade(DashboardService service) {
        this.service = service;
    }

    public DashboardResponse create(DashboardRequest request) {
        return service.create(request);
    }

    public DashboardResponse findById(Long id) {
        return service.findById(id);
    }

    public List<DashboardResponse> findAll() {
        return service.findAll();
    }

    public DashboardResponse update(Long id, DashboardRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
