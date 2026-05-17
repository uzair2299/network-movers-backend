package com.company.networkmovers.modules.maps.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MapsRequest {
    private String name;
    private String description;
}
