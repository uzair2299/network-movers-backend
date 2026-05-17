package com.company.networkmovers.modules.report.service;

import com.company.networkmovers.modules.report.dto.request.ReportRequest;
import com.company.networkmovers.modules.report.dto.response.ReportResponse;
import java.util.List;

public interface ReportService {
    ReportResponse create(ReportRequest request);
    ReportResponse findById(Long id);
    List<ReportResponse> findAll();
    ReportResponse update(Long id, ReportRequest request);
    void delete(Long id);
}
