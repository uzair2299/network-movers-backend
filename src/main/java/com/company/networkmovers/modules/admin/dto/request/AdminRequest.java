package com.company.networkmovers.modules.admin.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminRequest {
    private String name;
    private String description;
}
