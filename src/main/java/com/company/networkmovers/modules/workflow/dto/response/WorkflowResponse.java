package com.company.networkmovers.modules.workflow.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Long createdBy;
}
