package com.company.networkmovers.modules.audit.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditRequest {
    private String name;
    private String description;
}
