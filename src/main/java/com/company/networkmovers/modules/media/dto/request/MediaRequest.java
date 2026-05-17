package com.company.networkmovers.modules.media.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaRequest {
    private String name;
    private String description;
}
