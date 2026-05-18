package com.company.networkmovers.modules.maps.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MapsResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Long createdBy;
}
