package com.company.networkmovers.modules.attendance.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRequest {
    private String name;
    private String description;
}
