package com.company.networkmovers.modules.booking.mapper;

import com.company.networkmovers.modules.booking.entity.BookingEntity;
import com.company.networkmovers.modules.booking.dto.request.BookingRequest;
import com.company.networkmovers.modules.booking.dto.response.BookingResponse;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingEntity toEntity(BookingRequest request) {
        if (request == null) return null;
        return BookingEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public BookingResponse toResponse(BookingEntity entity) {
        if (entity == null) return null;
        return BookingResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
