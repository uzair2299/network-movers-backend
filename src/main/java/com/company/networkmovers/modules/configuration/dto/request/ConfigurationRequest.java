package com.company.networkmovers.modules.configuration.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigurationRequest {
    private String name;
    private String description;
}
