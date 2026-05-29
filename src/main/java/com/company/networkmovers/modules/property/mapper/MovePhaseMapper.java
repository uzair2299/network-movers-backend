package com.company.networkmovers.modules.property.mapper;

import com.company.networkmovers.modules.property.entity.MovePhase;
import com.company.networkmovers.modules.property.dto.request.MovePhaseRequest;
import com.company.networkmovers.modules.property.dto.response.MovePhaseResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class MovePhaseMapper implements GenericMapper<MovePhase, MovePhaseRequest, MovePhaseResponse> {

    @Override
    public MovePhase toEntity(MovePhaseRequest request) {
        if (request == null) return null;
        return MovePhase.builder()
                .name(request.getName())
                .code(request.getCode())
                .active(request.isActive())
                .sequenceNo(request.getSequenceNo())
                .build();
    }

    @Override
    public MovePhaseResponse toResponse(MovePhase entity) {
        if (entity == null) return null;
        return MovePhaseResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .active(entity.isActive())
                .sequenceNo(entity.getSequenceNo())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
