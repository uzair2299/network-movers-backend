package com.company.networkmovers.modules.backup.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BackupRequest {
    private String name;
    private String description;
}
