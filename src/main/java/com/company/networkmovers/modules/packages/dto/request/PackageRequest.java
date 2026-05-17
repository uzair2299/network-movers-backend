package com.company.networkmovers.modules.packages.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageRequest {
    private String name;
    private String description;
}
