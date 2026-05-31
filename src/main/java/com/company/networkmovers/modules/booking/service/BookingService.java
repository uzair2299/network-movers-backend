package com.company.networkmovers.modules.booking.service;

import com.company.networkmovers.modules.booking.dto.request.BookingRequest;
import com.company.networkmovers.modules.booking.dto.response.BookingResponse;
import java.util.List;

public interface BookingService {
    BookingResponse create(BookingRequest request);
    BookingResponse findById(Long id);
    org.springframework.data.domain.Page<BookingResponse> getAll(com.company.networkmovers.shared.dto.RequestParamDto requestParams);
    org.springframework.data.domain.Page<BookingResponse> getAllActive(com.company.networkmovers.shared.dto.RequestParamDto requestParams);
    org.springframework.data.domain.Page<BookingResponse> getAllByUserId(Long userId, com.company.networkmovers.shared.dto.RequestParamDto requestParams);
    BookingResponse findByIdAndUserId(Long id, Long userId);
    BookingResponse update(Long id, BookingRequest request);
    void delete(Long id);
}
