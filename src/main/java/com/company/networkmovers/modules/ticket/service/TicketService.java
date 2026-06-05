package com.company.networkmovers.modules.ticket.service;

import java.util.UUID;

import com.company.networkmovers.modules.ticket.dto.request.TicketRequest;
import com.company.networkmovers.modules.ticket.dto.response.TicketResponse;
import java.util.List;

public interface TicketService {
    TicketResponse create(TicketRequest request);
    TicketResponse findById(UUID id);
    List<TicketResponse> findAll();
    TicketResponse update(UUID id, TicketRequest request);
    void delete(UUID id);
}
