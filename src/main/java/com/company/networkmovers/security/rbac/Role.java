package com.company.networkmovers.security.rbac;

import com.company.networkmovers.shared.entity.BaseSoftDeleteEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "sec_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@SQLRestriction("deleted = false")
public class Role extends BaseSoftDeleteEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;
}
