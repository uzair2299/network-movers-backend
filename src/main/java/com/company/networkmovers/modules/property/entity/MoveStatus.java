package com.company.networkmovers.modules.property.entity;

import com.company.networkmovers.shared.entity.BaseLookupEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "move_status")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MoveStatus extends BaseLookupEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phase_id", nullable = false)
    private MovePhase phase;

    @Column(name = "description")
    private String description;

    @Column(name = "sequence_no")
    private Integer sequenceNo;

    @Column(name = "is_final", nullable = false)
    @Builder.Default
    private boolean isFinal = false;

    @Column(name = "color_code")
    private String colorCode;

    @Column(name = "customer_visible", nullable = false)
    @Builder.Default
    private boolean customerVisible = true;

    @Column(name = "internal_only", nullable = false)
    @Builder.Default
    private boolean internalOnly = false;
}
