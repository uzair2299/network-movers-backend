package com.company.networkmovers.modules.rbac.dto.request;

import lombok.*;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceRequest {
    private String code;
    private String name;
    private String description;
    private UUID moduleId;
    @Builder.Default
    private boolean active = true;
}
