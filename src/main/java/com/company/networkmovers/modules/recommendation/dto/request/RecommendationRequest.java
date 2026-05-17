package com.company.networkmovers.modules.recommendation.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationRequest {
    private String name;
    private String description;
}
