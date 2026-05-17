package com.company.networkmovers.modules.contract.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractRequest {
    private String name;
    private String description;
}
