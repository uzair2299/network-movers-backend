package com.company.networkmovers.modules.booking.dto.response;

import com.company.networkmovers.modules.property.dto.response.MoveStatusResponse;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingHistoryResponse {
    private Long id;
    private Long bookingId;
    private MoveStatusResponse previousStatus;
    private MoveStatusResponse newStatus;
    private String notes;
    private LocalDateTime createdAt;
    private Long createdBy;
}
