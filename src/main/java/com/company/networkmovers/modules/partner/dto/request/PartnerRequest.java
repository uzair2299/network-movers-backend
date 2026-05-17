package com.company.networkmovers.modules.partner.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerRequest {
    private String name;
    private String description;
}
