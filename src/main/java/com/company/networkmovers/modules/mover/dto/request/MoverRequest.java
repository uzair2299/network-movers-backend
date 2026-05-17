package com.company.networkmovers.modules.mover.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoverRequest {
    private String name;
    private String description;
}
