package com.company.networkmovers.modules.workflow.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowRequest {
    private String name;
    private String description;
}
