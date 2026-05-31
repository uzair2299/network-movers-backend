package com.company.networkmovers.modules.booking.service;

import com.company.networkmovers.modules.booking.dto.request.BookingRequest;
import com.company.networkmovers.modules.booking.dto.response.BookingResponse;
import java.util.List;

public interface BookingService {
    BookingResponse create(BookingRequest request);
    BookingResponse findById(Long id);
    org.springframework.data.domain.Page<BookingResponse> getAll(com.company.networkmovers.shared.dto.RequestParamDto requestParams);
    List<BookingResponse> getAllActive();
    List<BookingResponse> findAllByUserId(Long userId);
    BookingResponse findByIdAndUserId(Long id, Long userId);
    BookingResponse update(Long id, BookingRequest request);
    void delete(Long id);
}
