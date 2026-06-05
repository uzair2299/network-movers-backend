package com.company.networkmovers.modules.admin.dto.response;

import java.util.UUID;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminResponse {
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Long createdBy;
}
