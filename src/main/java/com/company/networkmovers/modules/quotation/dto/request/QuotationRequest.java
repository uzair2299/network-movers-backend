package com.company.networkmovers.modules.quotation.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuotationRequest {
    private String name;
    private String description;
}
