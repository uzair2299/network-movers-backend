package com.company.networkmovers.modules.booking.service;

import java.util.UUID;

import com.company.networkmovers.modules.booking.dto.request.BookingRequest;
import com.company.networkmovers.modules.booking.dto.response.BookingResponse;
import java.util.List;

public interface BookingService {
    BookingResponse create(BookingRequest request);
    BookingResponse findById(UUID id);
    org.springframework.data.domain.Page<BookingResponse> getAll(com.company.networkmovers.shared.dto.RequestParamDto requestParams);
    org.springframework.data.domain.Page<BookingResponse> getAllActive(com.company.networkmovers.shared.dto.RequestParamDto requestParams);
    org.springframework.data.domain.Page<BookingResponse> getAllByUserId(UUID userId, com.company.networkmovers.shared.dto.RequestParamDto requestParams);
    BookingResponse findByIdAndUserId(UUID id, UUID userId);
    BookingResponse update(UUID id, BookingRequest request);
    BookingResponse updateStatus(UUID id, com.company.networkmovers.modules.booking.dto.request.UpdateBookingStatusRequest request);
    List<com.company.networkmovers.modules.booking.dto.response.BookingHistoryResponse> getBookingTimeline(UUID id);
    void delete(UUID id);
}
