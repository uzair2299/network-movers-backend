package com.company.networkmovers.modules.claims.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaimsRequest {
    private String name;
    private String description;
}
