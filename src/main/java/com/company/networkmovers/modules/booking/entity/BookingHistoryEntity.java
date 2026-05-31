package com.company.networkmovers.modules.booking.entity;

import com.company.networkmovers.modules.property.entity.MoveStatus;
import com.company.networkmovers.shared.entity.BaseAuditEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tbl_booking_history", indexes = {
    @Index(name = "idx_booking_history_booking_created", columnList = "booking_id, created_at DESC")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BookingHistoryEntity extends BaseAuditEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    private BookingEntity booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "previous_status_id")
    private MoveStatus previousStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "new_status_id", nullable = false)
    private MoveStatus newStatus;

    @Column(name = "notes", length = 1000)
    private String notes;
}
