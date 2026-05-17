package com.company.networkmovers.modules.finance.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinanceRequest {
    private String name;
    private String description;
}
