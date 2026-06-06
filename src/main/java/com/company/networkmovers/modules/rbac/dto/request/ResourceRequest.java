package com.company.networkmovers.modules.rbac.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceRequest {
    private String code;
    private String name;
    private String description;
    @Builder.Default
    private boolean active = true;
}
