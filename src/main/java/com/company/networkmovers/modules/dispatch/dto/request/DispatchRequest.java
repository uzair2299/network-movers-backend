package com.company.networkmovers.modules.dispatch.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DispatchRequest {
    private String name;
    private String description;
}
