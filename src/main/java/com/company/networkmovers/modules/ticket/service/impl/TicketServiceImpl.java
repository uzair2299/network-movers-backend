package com.company.networkmovers.modules.ticket.service.impl;

import com.company.networkmovers.modules.ticket.entity.TicketEntity;
import com.company.networkmovers.modules.ticket.repository.TicketRepository;
import com.company.networkmovers.modules.ticket.service.TicketService;
import com.company.networkmovers.modules.ticket.dto.request.TicketRequest;
import com.company.networkmovers.modules.ticket.dto.response.TicketResponse;
import com.company.networkmovers.modules.ticket.mapper.TicketMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private final TicketRepository repository;
    private final TicketMapper mapper;

    public TicketServiceImpl(TicketRepository repository, TicketMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TicketResponse create(TicketRequest request) {
        TicketEntity entity = mapper.toEntity(request);
        TicketEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public TicketResponse findById(Long id) {
        TicketEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TicketResponse update(Long id, TicketRequest request) {
        TicketEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        TicketEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        TicketEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));
        repository.delete(entity);
    }
}
