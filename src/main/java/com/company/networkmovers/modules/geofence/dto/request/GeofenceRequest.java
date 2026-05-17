package com.company.networkmovers.modules.geofence.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeofenceRequest {
    private String name;
    private String description;
}
