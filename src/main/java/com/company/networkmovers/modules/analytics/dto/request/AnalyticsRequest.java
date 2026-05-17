package com.company.networkmovers.modules.analytics.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalyticsRequest {
    private String name;
    private String description;
}
