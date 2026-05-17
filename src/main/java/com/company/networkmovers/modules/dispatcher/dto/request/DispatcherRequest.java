package com.company.networkmovers.modules.dispatcher.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DispatcherRequest {
    private String name;
    private String description;
}
