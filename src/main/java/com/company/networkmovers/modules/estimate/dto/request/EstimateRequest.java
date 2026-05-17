package com.company.networkmovers.modules.estimate.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstimateRequest {
    private String name;
    private String description;
}
