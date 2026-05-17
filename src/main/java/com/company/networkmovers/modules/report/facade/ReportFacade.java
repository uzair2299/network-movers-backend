package com.company.networkmovers.modules.report.facade;

import com.company.networkmovers.modules.report.dto.request.ReportRequest;
import com.company.networkmovers.modules.report.dto.response.ReportResponse;
import com.company.networkmovers.modules.report.service.ReportService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ReportFacade {

    private final ReportService service;

    public ReportFacade(ReportService service) {
        this.service = service;
    }

    public ReportResponse create(ReportRequest request) {
        return service.create(request);
    }

    public ReportResponse findById(Long id) {
        return service.findById(id);
    }

    public List<ReportResponse> findAll() {
        return service.findAll();
    }

    public ReportResponse update(Long id, ReportRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
