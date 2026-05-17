package com.company.networkmovers.modules.hr.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HrRequest {
    private String name;
    private String description;
}
