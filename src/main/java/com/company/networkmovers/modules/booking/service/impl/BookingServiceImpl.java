package com.company.networkmovers.modules.booking.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.company.networkmovers.security.context.CustomUserDetails;
import com.company.networkmovers.modules.identity.entity.User;
import com.company.networkmovers.modules.booking.entity.BookingEntity;
import com.company.networkmovers.modules.booking.entity.ScheduleType;
import com.company.networkmovers.modules.booking.repository.BookingRepository;
import com.company.networkmovers.modules.booking.service.BookingService;
import com.company.networkmovers.modules.booking.dto.request.BookingRequest;
import com.company.networkmovers.modules.booking.dto.response.BookingResponse;
import com.company.networkmovers.modules.booking.mapper.BookingMapper;
import com.company.networkmovers.modules.property.entity.*;
import com.company.networkmovers.modules.property.repository.MoveStatusRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;
    private final BookingMapper mapper;
    private final EntityManager entityManager;
    private final MoveStatusRepository moveStatusRepository;

    public BookingServiceImpl(BookingRepository repository, BookingMapper mapper, EntityManager entityManager, MoveStatusRepository moveStatusRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.entityManager = entityManager;
        this.moveStatusRepository = moveStatusRepository;
    }

    @Override
    public BookingResponse create(BookingRequest request) {
        BookingEntity entity = mapper.toEntity(request);

        // Always initialize new bookings with the 'REQUESTED' status on the backend
        MoveStatus defaultStatus = moveStatusRepository.findByCode("REQUESTED")
                .orElseThrow(() -> new RuntimeException("Default MoveStatus 'REQUESTED' not found"));
        entity.setCurrentStatus(defaultStatus);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            entity.setUser(entityManager.getReference(User.class, userDetails.getId()));
        }

        BookingEntity saved = repository.save(entity);
        // Force flush to execute inserts
        entityManager.flush();
        // Fetch with all details loaded using JPQL fetch joins
        BookingEntity fetched = repository.findByIdWithDetails(saved.getId())
                .orElseThrow(() -> new RuntimeException("Booking not found after save with id: " + saved.getId()));
        return mapper.toResponse(fetched);
    }

    @Override
    @Transactional(readOnly = true)
    public BookingResponse findById(Long id) {
        BookingEntity entity = repository.findByIdWithDetails(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponse> findAll() {
        return repository.findAllWithDetails().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponse update(Long id, BookingRequest request) {
        BookingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        entity.setName(request.getName());
        entity.setDescription(request.getDescription());

        if (request.getCurrentStatusId() != null) {
            entity.setCurrentStatus(entityManager.getReference(MoveStatus.class, request.getCurrentStatusId()));
        }

        // Update route details
        if (request.getRouteDetails() != null) {
            BookingRequest.RouteDetails route = request.getRouteDetails();
            entity.setPickupAddress(route.getPickupAddress());
            entity.setPickupLatitude(route.getPickupLatitude());
            entity.setPickupLongitude(route.getPickupLongitude());
            entity.setDestinationAddress(route.getDestinationAddress());
            entity.setDestinationLatitude(route.getDestinationLatitude());
            entity.setDestinationLongitude(route.getDestinationLongitude());
            entity.setDistanceKm(route.getDistanceKm());
            entity.setDurationMinutes(route.getDurationMinutes());
        }

        // Update scheduling
        if (request.getScheduling() != null) {
            BookingRequest.Scheduling sched = request.getScheduling();
            entity.setScheduleType(ScheduleType.fromValue(sched.getScheduleType()));
            entity.setScheduledDate(sched.getScheduledDate());
            entity.setTimeSlot(sched.getTimeSlot());
        }

        // Update move specifications
        if (request.getMoveSpecifications() != null) {
            BookingRequest.MoveSpecifications spec = request.getMoveSpecifications();
            entity.setPropertyCategory(spec.getPropertyCategoryId() != null ?
                    entityManager.getReference(PropertyCategory.class, spec.getPropertyCategoryId()) : null);
            entity.setPropertyType(spec.getPropertyTypeId() != null ?
                    entityManager.getReference(PropertyType.class, spec.getPropertyTypeId()) : null);
            entity.setPropertySize(spec.getPropertySizeId() != null ?
                    entityManager.getReference(PropertySize.class, spec.getPropertySizeId()) : null);
        }

        // Update access details
        if (request.getAccessDetails() != null) {
            BookingRequest.AccessDetails access = request.getAccessDetails();
            
            // Pickup
            if (access.getPickup() != null) {
                BookingRequest.AccessDetails.AccessPoint pickup = access.getPickup();
                entity.setPickupFloorType(pickup.getFloorTypeId() != null ?
                        entityManager.getReference(FloorType.class, pickup.getFloorTypeId()) : null);
                entity.setPickupBuildingAccess(pickup.getBuildingAccessId() != null ?
                        entityManager.getReference(BuildingAccessType.class, pickup.getBuildingAccessId()) : null);
                entity.setPickupParkingAccess(pickup.getParkingAccessId() != null ?
                        entityManager.getReference(ParkingAccessType.class, pickup.getParkingAccessId()) : null);
                if (pickup.getRestrictionIds() != null) {
                    entity.setPickupRestrictions(pickup.getRestrictionIds().stream()
                            .map(rid -> entityManager.getReference(AccessRestrictionType.class, rid))
                            .collect(Collectors.toList()));
                } else {
                    entity.setPickupRestrictions(null);
                }
            }

            // Destination
            if (access.getDestination() != null) {
                BookingRequest.AccessDetails.AccessPoint dest = access.getDestination();
                entity.setDestinationFloorType(dest.getFloorTypeId() != null ?
                        entityManager.getReference(FloorType.class, dest.getFloorTypeId()) : null);
                entity.setDestinationBuildingAccess(dest.getBuildingAccessId() != null ?
                        entityManager.getReference(BuildingAccessType.class, dest.getBuildingAccessId()) : null);
                entity.setDestinationParkingAccess(dest.getParkingAccessId() != null ?
                        entityManager.getReference(ParkingAccessType.class, dest.getParkingAccessId()) : null);
                if (dest.getRestrictionIds() != null) {
                    entity.setDestinationRestrictions(dest.getRestrictionIds().stream()
                            .map(rid -> entityManager.getReference(AccessRestrictionType.class, rid))
                            .collect(Collectors.toList()));
                } else {
                    entity.setDestinationRestrictions(null);
                }
            }
        }

        BookingEntity updated = repository.save(entity);
        entityManager.flush();

        // Fetch with all details loaded
        BookingEntity fetched = repository.findByIdWithDetails(updated.getId())
                .orElseThrow(() -> new RuntimeException("Booking not found after update with id: " + updated.getId()));
        return mapper.toResponse(fetched);
    }

    @Override
    public void delete(Long id) {
        BookingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        repository.delete(entity);
    }
}
