package com.company.networkmovers.modules.approval.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprovalRequest {
    private String name;
    private String description;
}
