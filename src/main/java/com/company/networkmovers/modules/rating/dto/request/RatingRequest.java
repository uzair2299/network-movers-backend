package com.company.networkmovers.modules.rating.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingRequest {
    private String name;
    private String description;
}
