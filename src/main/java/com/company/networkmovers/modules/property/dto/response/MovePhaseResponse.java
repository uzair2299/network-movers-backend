package com.company.networkmovers.modules.property.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovePhaseResponse {
    private UUID id;
    private String name;
    private String code;
    private boolean active;
    private Integer sequenceNo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
