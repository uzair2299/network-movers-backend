package com.company.networkmovers.modules.driver.dto.response;

import java.util.UUID;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverResponse {
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Long createdBy;
}
