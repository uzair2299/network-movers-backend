package com.company.networkmovers.modules.document.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentRequest {
    private String name;
    private String description;
}
