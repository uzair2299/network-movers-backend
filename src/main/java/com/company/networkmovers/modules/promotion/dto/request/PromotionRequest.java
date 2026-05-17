package com.company.networkmovers.modules.promotion.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionRequest {
    private String name;
    private String description;
}
