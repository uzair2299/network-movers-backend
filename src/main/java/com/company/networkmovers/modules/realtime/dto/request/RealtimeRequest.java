package com.company.networkmovers.modules.realtime.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RealtimeRequest {
    private String name;
    private String description;
}
