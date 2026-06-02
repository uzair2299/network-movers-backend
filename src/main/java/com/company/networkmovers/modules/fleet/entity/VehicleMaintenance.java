package com.company.networkmovers.modules.fleet.entity;

import com.company.networkmovers.shared.entity.BaseUuidSoftDeleteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "vehicle_maintenance")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleMaintenance extends BaseUuidSoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_type_id", nullable = false)
    private VehicleMaintenanceType maintenanceType;

    @Column(name = "maintenance_date", nullable = false)
    private LocalDate maintenanceDate;

    @Column(name = "odometer_km")
    private BigDecimal odometerKm;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "next_service_date")
    private LocalDate nextServiceDate;

    @Column(name = "next_service_km")
    private BigDecimal nextServiceKm;

    @Column(name = "remarks")
    private String remarks;
}
