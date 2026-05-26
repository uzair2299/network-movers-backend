package com.company.networkmovers.shared.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Pagination, sorting, and dynamic search filter parameters")
public class RequestParamDto {

    @Builder.Default
    @Schema(description = "Zero-based page index (0..N)", defaultValue = "0", example = "0")
    private int page = 0;
    
    @Builder.Default
    @Schema(description = "The size of the page to be returned", defaultValue = "20", example = "20")
    private int size = 20;
    
    @Schema(description = "Dynamic search term to match against name or code (case-insensitive)", example = "office")
    private String search;
    
    @Builder.Default
    @Schema(description = "Sorting criteria in the format: property,(asc|desc).", defaultValue = "name,asc", example = "name,asc")
    private String sort = "name,asc";
}
