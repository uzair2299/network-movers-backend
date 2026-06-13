package com.company.networkmovers.modules.property.entity;

import com.company.networkmovers.modules.rbac.entity.Role;
import com.company.networkmovers.shared.entity.BaseUuidSoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "move_status_transition")
@SQLRestriction("deleted = false")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MoveStatusTransition extends BaseUuidSoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_status_id", nullable = false)
    private MoveStatus fromStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_status_id", nullable = false)
    private MoveStatus toStatus;

    @Column(name = "transition_name")
    private String transitionName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "allowed_role_id")
    private Role allowedRole;

    @Column(name = "requires_approval", nullable = false)
    @Builder.Default
    private boolean requiresApproval = false;

    @Column(name = "customer_visible", nullable = false)
    @Builder.Default
    private boolean customerVisible = true;

    @Column(name = "active", nullable = false)
    @Builder.Default
    private boolean active = true;
}
