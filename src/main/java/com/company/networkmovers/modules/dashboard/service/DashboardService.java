package com.company.networkmovers.modules.dashboard.service;

import java.util.UUID;

import com.company.networkmovers.modules.dashboard.dto.request.DashboardRequest;
import com.company.networkmovers.modules.dashboard.dto.response.DashboardResponse;
import java.util.List;

public interface DashboardService {
    DashboardResponse create(DashboardRequest request);
    DashboardResponse findById(UUID id);
    List<DashboardResponse> findAll();
    DashboardResponse update(UUID id, DashboardRequest request);
    void delete(UUID id);
}
