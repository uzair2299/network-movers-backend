package com.company.networkmovers.modules.asset.service;

import com.company.networkmovers.modules.asset.dto.request.StockAuditRequest;
import com.company.networkmovers.modules.asset.dto.response.StockAuditResponse;
import com.company.networkmovers.shared.dto.RequestParamDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface StockAuditService {
    StockAuditResponse create(StockAuditRequest request);
    StockAuditResponse update(UUID id, StockAuditRequest request);
    StockAuditResponse getById(UUID id);
    Page<StockAuditResponse> getAll(RequestParamDto requestParams);
    StockAuditResponse updateStatus(UUID id, String status);
    void delete(UUID id);
}
