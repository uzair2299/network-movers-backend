package com.company.networkmovers.modules.booking.entity;

import com.company.networkmovers.modules.identity.entity.User;
import com.company.networkmovers.shared.entity.BaseAuditEntity;
import com.company.networkmovers.modules.property.entity.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "tbl_booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BookingEntity extends BaseAuditEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_status_id")
    private MoveStatus currentStatus;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "pickup_address")
    private String pickupAddress;

    @Column(name = "pickup_latitude")
    private Double pickupLatitude;

    @Column(name = "pickup_longitude")
    private Double pickupLongitude;

    @Column(name = "destination_address")
    private String destinationAddress;

    @Column(name = "destination_latitude")
    private Double destinationLatitude;

    @Column(name = "destination_longitude")
    private Double destinationLongitude;

    @Column(name = "distance_km")
    private Double distanceKm;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Enumerated(EnumType.STRING)
    @Column(name = "schedule_type")
    private ScheduleType scheduleType;

    @Column(name = "scheduled_date")
    private OffsetDateTime scheduledDate;

    @Column(name = "time_slot")
    private String timeSlot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_category_id")
    private PropertyCategory propertyCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_type_id")
    private PropertyType propertyType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_size_id")
    private PropertySize propertySize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickup_floor_type_id")
    private FloorType pickupFloorType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickup_building_access_id")
    private BuildingAccessType pickupBuildingAccess;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickup_parking_access_id")
    private ParkingAccessType pickupParkingAccess;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "tbl_booking_pickup_restrictions",
        joinColumns = @JoinColumn(name = "booking_id"),
        inverseJoinColumns = @JoinColumn(name = "restriction_id")
    )
    @org.hibernate.annotations.BatchSize(size = 20)
    private List<AccessRestrictionType> pickupRestrictions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_floor_type_id")
    private FloorType destinationFloorType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_building_access_id")
    private BuildingAccessType destinationBuildingAccess;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_parking_access_id")
    private ParkingAccessType destinationParkingAccess;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "tbl_booking_destination_restrictions",
        joinColumns = @JoinColumn(name = "booking_id"),
        inverseJoinColumns = @JoinColumn(name = "restriction_id")
    )
    @org.hibernate.annotations.BatchSize(size = 20)
    private List<AccessRestrictionType> destinationRestrictions;
}
