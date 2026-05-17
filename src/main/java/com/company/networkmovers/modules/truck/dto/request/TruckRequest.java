package com.company.networkmovers.modules.truck.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TruckRequest {
    private String name;
    private String description;
}
