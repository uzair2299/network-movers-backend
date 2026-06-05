package com.company.networkmovers.security.rbac;

import com.company.networkmovers.shared.entity.BaseLookupEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "sec_resources")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Resource extends BaseLookupEntity {

    @Column(name = "description")
    private String description;
}
