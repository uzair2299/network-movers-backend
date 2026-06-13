package com.company.networkmovers.modules.asset.service;

import com.company.networkmovers.modules.asset.dto.request.EmployeeAssetAssignmentRequest;
import com.company.networkmovers.modules.asset.dto.response.EmployeeAssetAssignmentResponse;
import com.company.networkmovers.shared.dto.RequestParamDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface EmployeeAssetAssignmentService {
    EmployeeAssetAssignmentResponse create(EmployeeAssetAssignmentRequest request, UUID locationId);
    EmployeeAssetAssignmentResponse update(UUID id, EmployeeAssetAssignmentRequest request);
    EmployeeAssetAssignmentResponse getById(UUID id);
    Page<EmployeeAssetAssignmentResponse> getAll(RequestParamDto requestParams);
    EmployeeAssetAssignmentResponse updateStatus(UUID id, String status, UUID locationId, String conditionOnReturn);
    void delete(UUID id);
}
