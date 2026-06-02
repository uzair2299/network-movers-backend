package com.company.networkmovers.modules.fleet.entity;

import com.company.networkmovers.shared.entity.BaseLookupEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "vehicle_types")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleType extends BaseLookupEntity {

    @Column(name = "description")
    private String description;
}
