package com.company.networkmovers.modules.filemanagement.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilemanagementRequest {
    private String name;
    private String description;
}
