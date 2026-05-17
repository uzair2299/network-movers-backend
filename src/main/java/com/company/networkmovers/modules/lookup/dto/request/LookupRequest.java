package com.company.networkmovers.modules.lookup.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LookupRequest {
    private String name;
    private String description;
}
