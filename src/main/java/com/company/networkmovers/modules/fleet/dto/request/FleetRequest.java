package com.company.networkmovers.modules.fleet.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FleetRequest {
    private String name;
    private String description;
}
