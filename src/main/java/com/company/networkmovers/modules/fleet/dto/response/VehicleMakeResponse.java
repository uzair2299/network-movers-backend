package com.company.networkmovers.modules.fleet.dto.response;

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
public class VehicleMakeResponse {
    private UUID id;
    private String name;
    private String code;
    private boolean active;
    
    private String country;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
