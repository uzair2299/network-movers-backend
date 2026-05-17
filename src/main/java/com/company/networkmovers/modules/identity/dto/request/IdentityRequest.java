package com.company.networkmovers.modules.identity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdentityRequest {
    private String name;
    private String description;
}
