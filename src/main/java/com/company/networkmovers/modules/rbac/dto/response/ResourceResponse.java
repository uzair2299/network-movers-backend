package com.company.networkmovers.modules.rbac.dto.response;

import lombok.*;
import java.util.UUID;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceResponse {
    private UUID id;
    private String code;
    private String name;
    private String description;
    private UUID moduleId;
    private String moduleName;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
