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
public class MoveStatusResponse {
    private UUID id;
    private String name;
    private String code;
    private boolean active;
    private MovePhaseResponse phase;
    private String description;
    private Integer sequenceNo;
    private boolean isFinal;
    private String colorCode;
    private boolean customerVisible;
    private boolean internalOnly;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
