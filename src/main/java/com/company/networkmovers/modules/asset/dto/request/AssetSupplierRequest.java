package com.company.networkmovers.modules.asset.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetSupplierRequest {
    private String name;
    private String code;
    private String contactName;
    private String email;
    private String phone;
    private String address;
    @Builder.Default
    private boolean active = true;
}
