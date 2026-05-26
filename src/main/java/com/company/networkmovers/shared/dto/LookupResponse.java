package com.company.networkmovers.shared.dto;

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
public class LookupResponse {
    private UUID id;
    private String name;
    private String code;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
