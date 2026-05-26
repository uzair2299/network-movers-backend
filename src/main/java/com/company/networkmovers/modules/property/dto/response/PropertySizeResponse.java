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
public class PropertySizeResponse {
    private UUID id;
    private String name;
    private String code;
    private boolean active;
    private PropertyTypeResponse type;
    private String unitType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
