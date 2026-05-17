package com.company.networkmovers.modules.trip.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripRequest {
    private String name;
    private String description;
}
