package com.company.networkmovers.modules.estimate.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstimateResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Long createdBy;
}
