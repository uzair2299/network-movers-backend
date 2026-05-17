package com.company.networkmovers.modules.vehicle.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleRequest {
    private String name;
    private String description;
}
