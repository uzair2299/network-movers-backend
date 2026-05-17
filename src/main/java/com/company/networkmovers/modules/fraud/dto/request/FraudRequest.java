package com.company.networkmovers.modules.fraud.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FraudRequest {
    private String name;
    private String description;
}
