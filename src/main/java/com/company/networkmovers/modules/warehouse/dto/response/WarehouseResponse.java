package com.company.networkmovers.modules.warehouse.dto.response;

import java.util.UUID;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseResponse {
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Long createdBy;
}
