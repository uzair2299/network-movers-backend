package com.company.networkmovers.modules.inventory.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryRequest {
    private String name;
    private String description;
}
