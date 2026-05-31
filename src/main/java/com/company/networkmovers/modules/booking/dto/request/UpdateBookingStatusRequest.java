package com.company.networkmovers.modules.booking.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBookingStatusRequest {
    @NotNull(message = "Status ID is required")
    private UUID statusId;
    
    private String notes;
}
