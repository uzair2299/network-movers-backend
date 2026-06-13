package com.company.networkmovers.modules.asset.service;

import com.company.networkmovers.modules.asset.dto.request.DamageReportRequest;
import com.company.networkmovers.modules.asset.dto.response.DamageReportResponse;
import com.company.networkmovers.shared.dto.RequestParamDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface DamageReportService {
    DamageReportResponse create(DamageReportRequest request);
    DamageReportResponse update(UUID id, DamageReportRequest request);
    DamageReportResponse getById(UUID id);
    Page<DamageReportResponse> getAll(RequestParamDto requestParams);
    void delete(UUID id);
}
