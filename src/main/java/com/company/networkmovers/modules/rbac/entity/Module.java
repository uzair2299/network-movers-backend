package com.company.networkmovers.modules.rbac.entity;

import com.company.networkmovers.shared.entity.BaseLookupEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "modules", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"})
})
@SQLRestriction("deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Module extends BaseLookupEntity {

    @Column(name = "description")
    private String description;
}
