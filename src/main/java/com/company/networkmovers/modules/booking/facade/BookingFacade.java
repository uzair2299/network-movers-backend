package com.company.networkmovers.modules.booking.facade;

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

    public BookingResponse findById(Long id) {
        return service.findById(id);
    }

    public List<BookingResponse> findAll() {
        return service.findAll();
    }

    public BookingResponse update(Long id, BookingRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
