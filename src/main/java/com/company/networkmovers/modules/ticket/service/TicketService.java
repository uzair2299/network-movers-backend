package com.company.networkmovers.modules.ticket.service;

import com.company.networkmovers.modules.ticket.dto.request.TicketRequest;
import com.company.networkmovers.modules.ticket.dto.response.TicketResponse;
import java.util.List;

public interface TicketService {
    TicketResponse create(TicketRequest request);
    TicketResponse findById(Long id);
    List<TicketResponse> findAll();
    TicketResponse update(Long id, TicketRequest request);
    void delete(Long id);
}
