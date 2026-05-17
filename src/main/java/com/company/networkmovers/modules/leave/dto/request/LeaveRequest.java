package com.company.networkmovers.modules.leave.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequest {
    private String name;
    private String description;
}
