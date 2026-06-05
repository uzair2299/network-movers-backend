package com.company.networkmovers.modules.ticket.dto.response;

import java.util.UUID;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponse {
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Long createdBy;
}
