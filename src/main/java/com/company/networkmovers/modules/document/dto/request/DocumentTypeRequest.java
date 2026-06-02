package com.company.networkmovers.modules.document.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypeRequest {
    private String name;
    private String code;
    @Builder.Default
    private boolean active = true;
    
    @Builder.Default
    private boolean mandatory = false;
    
    @Builder.Default
    private boolean expiryRequired = true;
    
    private String description;
}
