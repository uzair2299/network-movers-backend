package com.company.networkmovers.modules.insurance.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsuranceRequest {
    private String name;
    private String description;
}
