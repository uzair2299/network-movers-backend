package com.company.networkmovers.modules.report.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportRequest {
    private String name;
    private String description;
}
