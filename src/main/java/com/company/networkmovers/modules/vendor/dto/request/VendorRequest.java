package com.company.networkmovers.modules.vendor.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorRequest {
    private String name;
    private String description;
}
