package com.company.networkmovers.modules.fleet.entity;

import com.company.networkmovers.modules.fleet.entity.enums.OwnershipType;
import com.company.networkmovers.modules.fleet.entity.enums.VehicleStatus;
import com.company.networkmovers.shared.entity.BaseUuidSoftDeleteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends BaseUuidSoftDeleteEntity {

    @Column(name = "vehicle_code", nullable = false, unique = true)
    private String vehicleCode;

    @Column(name = "registration_no", nullable = false, unique = true)
    private String registrationNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_model_id", nullable = false)
    private VehicleModel vehicleModel;

    @Column(name = "manufacture_year")
    private Integer manufactureYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "ownership_type", nullable = false)
    private OwnershipType ownershipType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private VehicleStatus status;

    @Column(name = "current_odometer_km")
    private BigDecimal currentOdometerKm;

    @Column(name = "insurance_expiry_date")
    private LocalDate insuranceExpiryDate;

    @Column(name = "fitness_expiry_date")
    private LocalDate fitnessExpiryDate;

    @Column(name = "acquisition_date")
    private LocalDate acquisitionDate;

    @Column(name = "active", nullable = false)
    @Builder.Default
    private boolean active = true;

    @Column(name = "remarks")
    private String remarks;
}
