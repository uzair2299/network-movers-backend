package com.company.networkmovers.modules.invoice.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceRequest {
    private String name;
    private String description;
}
