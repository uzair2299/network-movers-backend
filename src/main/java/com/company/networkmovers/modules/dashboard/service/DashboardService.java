package com.company.networkmovers.modules.dashboard.service;

import com.company.networkmovers.modules.dashboard.dto.request.DashboardRequest;
import com.company.networkmovers.modules.dashboard.dto.response.DashboardResponse;
import java.util.List;

public interface DashboardService {
    DashboardResponse create(DashboardRequest request);
    DashboardResponse findById(Long id);
    List<DashboardResponse> findAll();
    DashboardResponse update(Long id, DashboardRequest request);
    void delete(Long id);
}
