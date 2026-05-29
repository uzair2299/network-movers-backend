package com.company.networkmovers.modules.booking.mapper;

import com.company.networkmovers.modules.booking.dto.request.BookingRequest;
import com.company.networkmovers.modules.booking.dto.response.BookingResponse;
import com.company.networkmovers.modules.booking.entity.BookingEntity;
import com.company.networkmovers.modules.booking.entity.ScheduleType;
import com.company.networkmovers.modules.property.entity.*;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.company.networkmovers.modules.property.mapper.MoveStatusMapper;

@Component
public class BookingMapper {

    private final EntityManager entityManager;
    private final MoveStatusMapper moveStatusMapper;

    public BookingMapper(EntityManager entityManager, MoveStatusMapper moveStatusMapper) {
        this.entityManager = entityManager;
        this.moveStatusMapper = moveStatusMapper;
    }

    public BookingEntity toEntity(BookingRequest request) {
        if (request == null) return null;

        BookingEntity.BookingEntityBuilder<?, ?> builder = BookingEntity.builder();

        builder.name(request.getName());
        builder.description(request.getDescription());

        if (request.getCurrentStatusId() != null) {
            builder.currentStatus(entityManager.getReference(MoveStatus.class, request.getCurrentStatusId()));
        }

        // Map route details if present
        if (request.getRouteDetails() != null) {
            BookingRequest.RouteDetails route = request.getRouteDetails();
            builder.pickupAddress(route.getPickupAddress());
            builder.pickupLatitude(route.getPickupLatitude());
            builder.pickupLongitude(route.getPickupLongitude());
            builder.destinationAddress(route.getDestinationAddress());
            builder.destinationLatitude(route.getDestinationLatitude());
            builder.destinationLongitude(route.getDestinationLongitude());
            builder.distanceKm(route.getDistanceKm());
            builder.durationMinutes(route.getDurationMinutes());
        }

        // Map scheduling if present
        if (request.getScheduling() != null) {
            BookingRequest.Scheduling sched = request.getScheduling();
            builder.scheduleType(ScheduleType.fromValue(sched.getScheduleType()));
            builder.scheduledDate(sched.getScheduledDate());
            builder.timeSlot(sched.getTimeSlot());
        }

        // Map move specifications if present
        if (request.getMoveSpecifications() != null) {
            BookingRequest.MoveSpecifications spec = request.getMoveSpecifications();
            if (spec.getPropertyCategoryId() != null) {
                builder.propertyCategory(entityManager.getReference(PropertyCategory.class, spec.getPropertyCategoryId()));
            }
            if (spec.getPropertyTypeId() != null) {
                builder.propertyType(entityManager.getReference(PropertyType.class, spec.getPropertyTypeId()));
            }
            if (spec.getPropertySizeId() != null) {
                builder.propertySize(entityManager.getReference(PropertySize.class, spec.getPropertySizeId()));
            }
        }

        // Map access details if present
        if (request.getAccessDetails() != null) {
            BookingRequest.AccessDetails access = request.getAccessDetails();

            // Pickup Access Point
            if (access.getPickup() != null) {
                BookingRequest.AccessDetails.AccessPoint pickup = access.getPickup();
                if (pickup.getFloorTypeId() != null) {
                    builder.pickupFloorType(entityManager.getReference(FloorType.class, pickup.getFloorTypeId()));
                }
                if (pickup.getBuildingAccessId() != null) {
                    builder.pickupBuildingAccess(entityManager.getReference(BuildingAccessType.class, pickup.getBuildingAccessId()));
                }
                if (pickup.getParkingAccessId() != null) {
                    builder.pickupParkingAccess(entityManager.getReference(ParkingAccessType.class, pickup.getParkingAccessId()));
                }
                if (pickup.getRestrictionIds() != null && !pickup.getRestrictionIds().isEmpty()) {
                    List<AccessRestrictionType> restrictions = pickup.getRestrictionIds().stream()
                            .map(id -> entityManager.getReference(AccessRestrictionType.class, id))
                            .collect(Collectors.toList());
                    builder.pickupRestrictions(restrictions);
                }
            }

            // Destination Access Point
            if (access.getDestination() != null) {
                BookingRequest.AccessDetails.AccessPoint dest = access.getDestination();
                if (dest.getFloorTypeId() != null) {
                    builder.destinationFloorType(entityManager.getReference(FloorType.class, dest.getFloorTypeId()));
                }
                if (dest.getBuildingAccessId() != null) {
                    builder.destinationBuildingAccess(entityManager.getReference(BuildingAccessType.class, dest.getBuildingAccessId()));
                }
                if (dest.getParkingAccessId() != null) {
                    builder.destinationParkingAccess(entityManager.getReference(ParkingAccessType.class, dest.getParkingAccessId()));
                }
                if (dest.getRestrictionIds() != null && !dest.getRestrictionIds().isEmpty()) {
                    List<AccessRestrictionType> restrictions = dest.getRestrictionIds().stream()
                            .map(id -> entityManager.getReference(AccessRestrictionType.class, id))
                            .collect(Collectors.toList());
                    builder.destinationRestrictions(restrictions);
                }
            }
        }

        return builder.build();
    }

    public BookingResponse toResponse(BookingEntity entity) {
        if (entity == null) return null;

        BookingResponse.BookingResponseBuilder builder = BookingResponse.builder();

        builder.id(entity.getId());
        builder.userId(entity.getUser() != null ? entity.getUser().getId() : null);
        builder.name(entity.getName());
        builder.description(entity.getDescription());
        builder.createdAt(entity.getCreatedAt());
        builder.createdBy(entity.getCreatedBy());

        if (entity.getCurrentStatus() != null) {
            builder.currentStatus(moveStatusMapper.toResponse(entity.getCurrentStatus()));
        }

        // Route Details Response
        builder.routeDetails(BookingResponse.RouteDetailsResponse.builder()
                .pickupAddress(entity.getPickupAddress())
                .pickupLatitude(entity.getPickupLatitude())
                .pickupLongitude(entity.getPickupLongitude())
                .destinationAddress(entity.getDestinationAddress())
                .destinationLatitude(entity.getDestinationLatitude())
                .destinationLongitude(entity.getDestinationLongitude())
                .distanceKm(entity.getDistanceKm())
                .durationMinutes(entity.getDurationMinutes())
                .build());

        // Scheduling Response
        builder.scheduling(BookingResponse.SchedulingResponse.builder()
                .scheduleType(entity.getScheduleType())
                .scheduledDate(entity.getScheduledDate())
                .timeSlot(entity.getTimeSlot())
                .build());

        // Move Specifications Response
        BookingResponse.MoveSpecificationsResponse.MoveSpecificationsResponseBuilder specBuilder =
                BookingResponse.MoveSpecificationsResponse.builder();
        if (entity.getPropertyCategory() != null) {
            PropertyCategory cat = entity.getPropertyCategory();
            specBuilder.propertyCategoryId(cat.getId());
            specBuilder.propertyCategoryCode(cat.getCode());
            specBuilder.propertyCategoryName(cat.getName());
        }
        if (entity.getPropertyType() != null) {
            PropertyType type = entity.getPropertyType();
            specBuilder.propertyTypeId(type.getId());
            specBuilder.propertyTypeCode(type.getCode());
            specBuilder.propertyTypeName(type.getName());
        }
        if (entity.getPropertySize() != null) {
            PropertySize size = entity.getPropertySize();
            specBuilder.propertySizeId(size.getId());
            specBuilder.propertySizeCode(size.getCode());
            specBuilder.propertySizeName(size.getName());
        }
        builder.moveSpecifications(specBuilder.build());

        // Access Details Response
        BookingResponse.AccessDetailsResponse.AccessDetailsResponseBuilder accessBuilder =
                BookingResponse.AccessDetailsResponse.builder();

        // Pickup Access Point
        BookingResponse.AccessDetailsResponse.AccessPointResponse.AccessPointResponseBuilder pickupBuilder =
                BookingResponse.AccessDetailsResponse.AccessPointResponse.builder();
        if (entity.getPickupFloorType() != null) {
            FloorType ft = entity.getPickupFloorType();
            pickupBuilder.floorTypeId(ft.getId());
            pickupBuilder.floorTypeCode(ft.getCode());
            pickupBuilder.floorTypeName(ft.getName());
        }
        if (entity.getPickupBuildingAccess() != null) {
            BuildingAccessType bat = entity.getPickupBuildingAccess();
            pickupBuilder.buildingAccessId(bat.getId());
            pickupBuilder.buildingAccessCode(bat.getCode());
            pickupBuilder.buildingAccessName(bat.getName());
        }
        if (entity.getPickupParkingAccess() != null) {
            ParkingAccessType pat = entity.getPickupParkingAccess();
            pickupBuilder.parkingAccessId(pat.getId());
            pickupBuilder.parkingAccessCode(pat.getCode());
            pickupBuilder.parkingAccessName(pat.getName());
        }
        if (entity.getPickupRestrictions() != null) {
            List<BookingResponse.AccessDetailsResponse.RestrictionResponse> restrictions = entity.getPickupRestrictions().stream()
                    .map(r -> BookingResponse.AccessDetailsResponse.RestrictionResponse.builder()
                            .id(r.getId())
                            .code(r.getCode())
                            .name(r.getName())
                            .build())
                    .collect(Collectors.toList());
            pickupBuilder.restrictionDetails(restrictions);
        } else {
            pickupBuilder.restrictionDetails(new ArrayList<>());
        }
        accessBuilder.pickup(pickupBuilder.build());

        // Destination Access Point
        BookingResponse.AccessDetailsResponse.AccessPointResponse.AccessPointResponseBuilder destBuilder =
                BookingResponse.AccessDetailsResponse.AccessPointResponse.builder();
        if (entity.getDestinationFloorType() != null) {
            FloorType ft = entity.getDestinationFloorType();
            destBuilder.floorTypeId(ft.getId());
            destBuilder.floorTypeCode(ft.getCode());
            destBuilder.floorTypeName(ft.getName());
        }
        if (entity.getDestinationBuildingAccess() != null) {
            BuildingAccessType bat = entity.getDestinationBuildingAccess();
            destBuilder.buildingAccessId(bat.getId());
            destBuilder.buildingAccessCode(bat.getCode());
            destBuilder.buildingAccessName(bat.getName());
        }
        if (entity.getDestinationParkingAccess() != null) {
            ParkingAccessType pat = entity.getDestinationParkingAccess();
            destBuilder.parkingAccessId(pat.getId());
            destBuilder.parkingAccessCode(pat.getCode());
            destBuilder.parkingAccessName(pat.getName());
        }
        if (entity.getDestinationRestrictions() != null) {
            List<BookingResponse.AccessDetailsResponse.RestrictionResponse> restrictions = entity.getDestinationRestrictions().stream()
                    .map(r -> BookingResponse.AccessDetailsResponse.RestrictionResponse.builder()
                            .id(r.getId())
                            .code(r.getCode())
                            .name(r.getName())
                            .build())
                    .collect(Collectors.toList());
            destBuilder.restrictionDetails(restrictions);
        } else {
            destBuilder.restrictionDetails(new ArrayList<>());
        }
        accessBuilder.destination(destBuilder.build());

        builder.accessDetails(accessBuilder.build());

        return builder.build();
    }
}
