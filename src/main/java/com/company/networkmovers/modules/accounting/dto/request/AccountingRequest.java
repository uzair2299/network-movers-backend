package com.company.networkmovers.modules.accounting.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountingRequest {
    private String name;
    private String description;
}
