package com.company.networkmovers.modules.tracking.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackingRequest {
    private String name;
    private String description;
}
