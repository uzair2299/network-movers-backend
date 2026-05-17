package com.company.networkmovers.modules.ticket.mapper;

import com.company.networkmovers.modules.ticket.entity.TicketEntity;
import com.company.networkmovers.modules.ticket.dto.request.TicketRequest;
import com.company.networkmovers.modules.ticket.dto.response.TicketResponse;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketEntity toEntity(TicketRequest request) {
        if (request == null) return null;
        return TicketEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public TicketResponse toResponse(TicketEntity entity) {
        if (entity == null) return null;
        return TicketResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
