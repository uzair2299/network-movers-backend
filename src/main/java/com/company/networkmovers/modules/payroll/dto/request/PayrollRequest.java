package com.company.networkmovers.modules.payroll.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayrollRequest {
    private String name;
    private String description;
}
