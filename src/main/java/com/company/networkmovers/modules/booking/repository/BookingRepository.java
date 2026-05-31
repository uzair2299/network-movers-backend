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
           "LEFT JOIN FETCH b.user u " +
           "LEFT JOIN FETCH u.profile " +
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

    @Query(value = "SELECT b FROM BookingEntity b " +
           "LEFT JOIN FETCH b.user u " +
           "LEFT JOIN FETCH u.profile " +
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
           "LEFT JOIN FETCH b.destinationParkingAccess",
           countQuery = "SELECT count(b) FROM BookingEntity b")
    org.springframework.data.domain.Page<BookingEntity> findAllWithDetails(org.springframework.data.domain.Pageable pageable);

    @Query(value = "SELECT b FROM BookingEntity b " +
           "LEFT JOIN FETCH b.user u " +
           "LEFT JOIN FETCH u.profile " +
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
           "WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(b.description) LIKE LOWER(CONCAT('%', :search, '%'))",
           countQuery = "SELECT count(b) FROM BookingEntity b " +
           "WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(b.description) LIKE LOWER(CONCAT('%', :search, '%'))")
    org.springframework.data.domain.Page<BookingEntity> findBySearchWithDetails(@Param("search") String search, org.springframework.data.domain.Pageable pageable);

    @Query("SELECT b FROM BookingEntity b " +
           "LEFT JOIN FETCH b.user u " +
           "LEFT JOIN FETCH u.profile " +
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

    @Query("SELECT b FROM BookingEntity b " +
           "LEFT JOIN FETCH b.user u " +
           "LEFT JOIN FETCH u.profile " +
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
           "WHERE b.user.id = :userId")
    List<BookingEntity> findAllByUserIdWithDetails(@Param("userId") Long userId);

    @Query(value = "SELECT b FROM BookingEntity b " +
           "LEFT JOIN FETCH b.user u " +
           "LEFT JOIN FETCH u.profile " +
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
           "WHERE b.user.id = :userId",
           countQuery = "SELECT count(b) FROM BookingEntity b WHERE b.user.id = :userId")
    org.springframework.data.domain.Page<BookingEntity> findAllByUserIdWithDetails(@Param("userId") Long userId, org.springframework.data.domain.Pageable pageable);

    @Query(value = "SELECT b FROM BookingEntity b " +
           "LEFT JOIN FETCH b.user u " +
           "LEFT JOIN FETCH u.profile " +
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
           "WHERE b.user.id = :userId AND (" +
           "LOWER(b.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(b.description) LIKE LOWER(CONCAT('%', :search, '%')))",
           countQuery = "SELECT count(b) FROM BookingEntity b " +
           "WHERE b.user.id = :userId AND (" +
           "LOWER(b.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(b.description) LIKE LOWER(CONCAT('%', :search, '%')))")
    org.springframework.data.domain.Page<BookingEntity> findByUserIdAndSearchWithDetails(@Param("userId") Long userId, @Param("search") String search, org.springframework.data.domain.Pageable pageable);

    @Query("SELECT b FROM BookingEntity b " +
           "LEFT JOIN FETCH b.user u " +
           "LEFT JOIN FETCH u.profile " +
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
           "WHERE b.id = :id AND b.user.id = :userId")
    Optional<BookingEntity> findByIdAndUserIdWithDetails(@Param("id") Long id, @Param("userId") Long userId);
}
