package com.company.networkmovers.modules.property.mapper;

import com.company.networkmovers.modules.property.entity.MoveStatus;
import com.company.networkmovers.modules.property.dto.request.MoveStatusRequest;
import com.company.networkmovers.modules.property.dto.response.MoveStatusResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class MoveStatusMapper implements GenericMapper<MoveStatus, MoveStatusRequest, MoveStatusResponse> {

    private final MovePhaseMapper phaseMapper;

    public MoveStatusMapper(MovePhaseMapper phaseMapper) {
        this.phaseMapper = phaseMapper;
    }

    @Override
    public MoveStatus toEntity(MoveStatusRequest request) {
        if (request == null) return null;
        return MoveStatus.builder()
                .name(request.getName())
                .code(request.getCode())
                .active(request.isActive())
                .description(request.getDescription())
                .sequenceNo(request.getSequenceNo())
                .isFinal(request.isFinal())
                .colorCode(request.getColorCode())
                .customerVisible(request.isCustomerVisible())
                .internalOnly(request.isInternalOnly())
                .build();
    }

    @Override
    public MoveStatusResponse toResponse(MoveStatus entity) {
        if (entity == null) return null;
        return MoveStatusResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .active(entity.isActive())
                .phase(phaseMapper.toResponse(entity.getPhase()))
                .description(entity.getDescription())
                .sequenceNo(entity.getSequenceNo())
                .isFinal(entity.isFinal())
                .colorCode(entity.getColorCode())
                .customerVisible(entity.isCustomerVisible())
                .internalOnly(entity.isInternalOnly())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
