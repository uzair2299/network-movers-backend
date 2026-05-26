package com.company.networkmovers.modules.property.dto.response;

import com.company.networkmovers.shared.dto.LookupResponse;
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
public class PropertyTypeResponse {
    private UUID id;
    private String name;
    private String code;
    private boolean active;
    private LookupResponse category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
