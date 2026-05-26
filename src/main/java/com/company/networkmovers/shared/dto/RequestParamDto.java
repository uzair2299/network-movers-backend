package com.company.networkmovers.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestParamDto {
    @Builder.Default
    private int page = 0;
    
    @Builder.Default
    private int size = 20;
    
    private String search;
    
    private UUID categoryId;
    
    private UUID typeId;
    
    @Builder.Default
    private String sort = "name,asc";
}
