package com.company.networkmovers.modules.asset.dto.response;

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
public class AssetImageResponse {
    private UUID id;
    private String imageName;
    private String imagePath;
    private boolean isPrimary;
    private LocalDateTime createdAt;
}
