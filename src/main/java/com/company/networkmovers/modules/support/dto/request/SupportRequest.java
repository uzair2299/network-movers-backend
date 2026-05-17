package com.company.networkmovers.modules.support.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupportRequest {
    private String name;
    private String description;
}
