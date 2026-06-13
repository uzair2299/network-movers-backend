package com.company.networkmovers.modules.asset.mapper;

import com.company.networkmovers.modules.asset.entity.EmployeeAssetAssignment;
import com.company.networkmovers.modules.asset.dto.request.EmployeeAssetAssignmentRequest;
import com.company.networkmovers.modules.asset.dto.response.EmployeeAssetAssignmentResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeAssetAssignmentMapper implements GenericMapper<EmployeeAssetAssignment, EmployeeAssetAssignmentRequest, EmployeeAssetAssignmentResponse> {

    private final AssetMapper assetMapper;

    @Override
    public EmployeeAssetAssignment toEntity(EmployeeAssetAssignmentRequest request) {
        if (request == null) return null;
        return EmployeeAssetAssignment.builder()
                .employeeId(request.getEmployeeId())
                .assignedDate(request.getAssignedDate())
                .returnedDate(request.getReturnedDate())
                .conditionOnIssue(request.getConditionOnIssue())
                .conditionOnReturn(request.getConditionOnReturn())
                .remarks(request.getRemarks())
                .status(request.getStatus())
                .build();
    }

    @Override
    public EmployeeAssetAssignmentResponse toResponse(EmployeeAssetAssignment entity) {
        if (entity == null) return null;
        return EmployeeAssetAssignmentResponse.builder()
                .id(entity.getId())
                .employeeId(entity.getEmployeeId())
                .asset(assetMapper.toResponse(entity.getAsset()))
                .assignedDate(entity.getAssignedDate())
                .returnedDate(entity.getReturnedDate())
                .conditionOnIssue(entity.getConditionOnIssue())
                .conditionOnReturn(entity.getConditionOnReturn())
                .remarks(entity.getRemarks())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
