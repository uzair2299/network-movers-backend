package com.company.networkmovers.modules.booking.repository;

import com.company.networkmovers.modules.booking.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    @Query("SELECT b FROM BookingEntity b " +
           "LEFT JOIN FETCH b.user " +
           "LEFT JOIN FETCH b.currentStatus cs " +
           "LEFT JOIN FETCH cs.phase " +
           "LEFT JOIN FETCH b.propertyCategory " +
           "LEFT JOIN FETCH b.propertyType " +
           "LEFT JOIN FETCH b.propertySize " +
           "LEFT JOIN FETCH b.pickupFloorType " +
           "LEFT JOIN FETCH b.pickupBuildingAccess " +
           "LEFT JOIN FETCH b.pickupParkingAccess " +
           "LEFT JOIN FETCH b.destinationFloorType " +
           "LEFT JOIN FETCH b.destinationBuildingAccess " +
           "LEFT JOIN FETCH b.destinationParkingAccess " +
           "WHERE b.id = :id")
    Optional<BookingEntity> findByIdWithDetails(@Param("id") Long id);

    @Query("SELECT b FROM BookingEntity b " +
           "LEFT JOIN FETCH b.user " +
           "LEFT JOIN FETCH b.currentStatus cs " +
           "LEFT JOIN FETCH cs.phase " +
           "LEFT JOIN FETCH b.propertyCategory " +
           "LEFT JOIN FETCH b.propertyType " +
           "LEFT JOIN FETCH b.propertySize " +
           "LEFT JOIN FETCH b.pickupFloorType " +
           "LEFT JOIN FETCH b.pickupBuildingAccess " +
           "LEFT JOIN FETCH b.pickupParkingAccess " +
           "LEFT JOIN FETCH b.destinationFloorType " +
           "LEFT JOIN FETCH b.destinationBuildingAccess " +
           "LEFT JOIN FETCH b.destinationParkingAccess")
    List<BookingEntity> findAllWithDetails();
}
