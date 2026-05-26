package com.company.networkmovers.modules.property.entity;

import com.company.networkmovers.shared.entity.BaseLookupEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "parking_access_type")
@SuperBuilder
@NoArgsConstructor
public class ParkingAccessType extends BaseLookupEntity {
}
