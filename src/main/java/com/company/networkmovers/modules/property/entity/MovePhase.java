package com.company.networkmovers.modules.property.entity;

import com.company.networkmovers.shared.entity.BaseLookupEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "move_phase")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MovePhase extends BaseLookupEntity {

    @Column(name = "sequence_no", nullable = false)
    private Integer sequenceNo;

    @OneToMany(mappedBy = "phase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MoveStatus> statuses;
}
