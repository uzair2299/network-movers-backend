package com.company.networkmovers.modules.automation.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutomationRequest {
    private String name;
    private String description;
}
