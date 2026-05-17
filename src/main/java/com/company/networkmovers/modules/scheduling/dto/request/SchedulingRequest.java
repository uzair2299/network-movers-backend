package com.company.networkmovers.modules.scheduling.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchedulingRequest {
    private String name;
    private String description;
}
