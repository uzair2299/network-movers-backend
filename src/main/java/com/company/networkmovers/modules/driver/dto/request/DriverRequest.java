package com.company.networkmovers.modules.driver.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverRequest {
    private String name;
    private String description;
}
