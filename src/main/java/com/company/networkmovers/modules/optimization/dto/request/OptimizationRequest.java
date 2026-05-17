package com.company.networkmovers.modules.optimization.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptimizationRequest {
    private String name;
    private String description;
}
