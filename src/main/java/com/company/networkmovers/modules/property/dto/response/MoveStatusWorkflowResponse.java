package com.company.networkmovers.modules.property.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoveStatusWorkflowResponse {
    private List<MoveStatusNodeResponse> nodes;
    private List<MoveStatusTransitionResponse> transitions;
}
