package com.company.networkmovers.modules.warehouse.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseRequest {
    private String name;
    private String description;
}
