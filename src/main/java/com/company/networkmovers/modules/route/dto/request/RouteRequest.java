package com.company.networkmovers.modules.route.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteRequest {
    private String name;
    private String description;
}
