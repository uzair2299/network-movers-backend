package com.company.networkmovers.modules.dashboard.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardRequest {
    private String name;
    private String description;
}
