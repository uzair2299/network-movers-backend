package com.company.networkmovers.modules.property.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoveStatusWorkflowRequest {
    private List<MoveStatusNodeRequest> nodes;
    private List<MoveStatusTransitionRequest> transitions;
}
