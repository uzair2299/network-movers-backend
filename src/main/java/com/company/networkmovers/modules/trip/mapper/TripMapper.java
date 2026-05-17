package com.company.networkmovers.modules.trip.mapper;

import com.company.networkmovers.modules.trip.entity.TripEntity;
import com.company.networkmovers.modules.trip.dto.request.TripRequest;
import com.company.networkmovers.modules.trip.dto.response.TripResponse;
import org.springframework.stereotype.Component;

@Component
public class TripMapper {

    public TripEntity toEntity(TripRequest request) {
        if (request == null) return null;
        return TripEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public TripResponse toResponse(TripEntity entity) {
        if (entity == null) return null;
        return TripResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
