package com.company.networkmovers.modules.booking.facade;

import java.util.UUID;

import com.company.networkmovers.modules.booking.dto.request.BookingRequest;
import com.company.networkmovers.modules.booking.dto.response.BookingResponse;
import com.company.networkmovers.modules.booking.service.BookingService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class BookingFacade {

    private final BookingService service;

    public BookingFacade(BookingService service) {
        this.service = service;
    }

    public BookingResponse create(BookingRequest request) {
        return service.create(request);
    }

    public BookingResponse findById(UUID id) {
        return service.findById(id);
    }

    public org.springframework.data.domain.Page<BookingResponse> getAllActive(com.company.networkmovers.shared.dto.RequestParamDto requestParams) {
        return service.getAllActive(requestParams);
    }

    public BookingResponse update(UUID id, BookingRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
