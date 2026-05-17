package com.company.networkmovers.modules.settings.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingsRequest {
    private String name;
    private String description;
}
