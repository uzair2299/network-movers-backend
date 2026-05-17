package com.company.networkmovers.modules.ticket.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketRequest {
    private String name;
    private String description;
}
