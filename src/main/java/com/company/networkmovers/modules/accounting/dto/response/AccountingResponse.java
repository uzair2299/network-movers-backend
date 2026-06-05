package com.company.networkmovers.modules.accounting.dto.response;

import java.util.UUID;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountingResponse {
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Long createdBy;
}
