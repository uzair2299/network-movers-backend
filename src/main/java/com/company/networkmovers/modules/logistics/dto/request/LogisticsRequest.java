package com.company.networkmovers.modules.logistics.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogisticsRequest {
    private String name;
    private String description;
}
