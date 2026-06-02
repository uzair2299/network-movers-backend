package com.company.networkmovers.modules.fleet.entity;

import com.company.networkmovers.shared.entity.BaseUuidSoftDeleteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle_fuel_logs")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleFuelLog extends BaseUuidSoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(name = "fuel_date", nullable = false)
    private LocalDateTime fuelDate;

    @Column(name = "fuel_quantity_liters")
    private BigDecimal fuelQuantityLiters;

    @Column(name = "cost_amount")
    private BigDecimal costAmount;

    @Column(name = "odometer_km")
    private BigDecimal odometerKm;

    @Column(name = "fuel_station")
    private String fuelStation;

    @Column(name = "remarks")
    private String remarks;
}
