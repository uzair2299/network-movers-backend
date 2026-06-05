package com.company.networkmovers.modules.ticket.facade;

import java.util.UUID;

import com.company.networkmovers.modules.ticket.dto.request.TicketRequest;
import com.company.networkmovers.modules.ticket.dto.response.TicketResponse;
import com.company.networkmovers.modules.ticket.service.TicketService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TicketFacade {

    private final TicketService service;

    public TicketFacade(TicketService service) {
        this.service = service;
    }

    public TicketResponse create(TicketRequest request) {
        return service.create(request);
    }

    public TicketResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<TicketResponse> findAll() {
        return service.findAll();
    }

    public TicketResponse update(UUID id, TicketRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
