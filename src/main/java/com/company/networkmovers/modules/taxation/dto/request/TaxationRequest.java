package com.company.networkmovers.modules.taxation.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaxationRequest {
    private String name;
    private String description;
}
