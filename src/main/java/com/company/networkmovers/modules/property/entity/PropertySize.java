package com.company.networkmovers.modules.property.entity;

import com.company.networkmovers.shared.entity.BaseLookupEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "property_size")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PropertySize extends BaseLookupEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private PropertyType type;

    @Column(name = "unit_type", nullable = false)
    private String unitType;
}
