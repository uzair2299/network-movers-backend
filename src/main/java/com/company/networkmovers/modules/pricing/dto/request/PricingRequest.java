package com.company.networkmovers.modules.pricing.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PricingRequest {
    private String name;
    private String description;
}
