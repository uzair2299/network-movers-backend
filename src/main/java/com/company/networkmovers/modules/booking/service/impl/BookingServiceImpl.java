package com.company.networkmovers.modules.booking.service.impl;

import com.company.networkmovers.modules.booking.entity.BookingEntity;
import com.company.networkmovers.modules.booking.repository.BookingRepository;
import com.company.networkmovers.modules.booking.service.BookingService;
import com.company.networkmovers.modules.booking.dto.request.BookingRequest;
import com.company.networkmovers.modules.booking.dto.response.BookingResponse;
import com.company.networkmovers.modules.booking.mapper.BookingMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;
    private final BookingMapper mapper;

    public BookingServiceImpl(BookingRepository repository, BookingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public BookingResponse create(BookingRequest request) {
        BookingEntity entity = mapper.toEntity(request);
        BookingEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public BookingResponse findById(Long id) {
        BookingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponse update(Long id, BookingRequest request) {
        BookingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        BookingEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        BookingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        repository.delete(entity);
    }
}
