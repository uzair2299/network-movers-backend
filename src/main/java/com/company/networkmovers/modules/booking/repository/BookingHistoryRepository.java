package com.company.networkmovers.modules.booking.repository;

import com.company.networkmovers.modules.booking.entity.BookingHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingHistoryRepository extends JpaRepository<BookingHistoryEntity, Long> {

    @Query("SELECT h FROM BookingHistoryEntity h " +
           "LEFT JOIN FETCH h.previousStatus ps " +
           "LEFT JOIN FETCH h.newStatus ns " +
           "WHERE h.booking.id = :bookingId " +
           "ORDER BY h.createdAt DESC")
    List<BookingHistoryEntity> findByBookingIdOrderByCreatedAtDescWithDetails(@Param("bookingId") Long bookingId);
}
