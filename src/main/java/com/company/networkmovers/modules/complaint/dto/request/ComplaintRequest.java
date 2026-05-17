package com.company.networkmovers.modules.complaint.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplaintRequest {
    private String name;
    private String description;
}
