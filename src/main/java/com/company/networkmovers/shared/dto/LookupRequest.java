package com.company.networkmovers.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LookupRequest {
    private String name;
    private String code;
    
    @Builder.Default
    private boolean active = true;
}
