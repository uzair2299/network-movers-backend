package com.company.networkmovers.modules.ai.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiRequest {
    private String name;
    private String description;
}
