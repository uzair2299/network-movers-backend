package com.company.networkmovers.modules.communication.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunicationRequest {
    private String name;
    private String description;
}
