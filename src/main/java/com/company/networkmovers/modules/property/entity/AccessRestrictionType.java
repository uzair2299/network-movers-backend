package com.company.networkmovers.modules.property.entity;

import com.company.networkmovers.shared.entity.BaseLookupEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "access_restriction_type")
@SuperBuilder
@NoArgsConstructor
public class AccessRestrictionType extends BaseLookupEntity {
}
