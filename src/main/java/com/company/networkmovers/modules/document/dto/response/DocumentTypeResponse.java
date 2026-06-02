package com.company.networkmovers.modules.document.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypeResponse {
    private UUID id;
    private String name;
    private String code;
    private boolean active;
    
    private boolean mandatory;
    private boolean expiryRequired;
    private String description;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
