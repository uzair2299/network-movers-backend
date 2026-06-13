package com.company.networkmovers.modules.property.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoveStatusNodeRequest {
    private UUID id;
    private Integer x;
    private Integer y;
}
