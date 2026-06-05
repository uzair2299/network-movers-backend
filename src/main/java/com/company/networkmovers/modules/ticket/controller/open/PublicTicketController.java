package com.company.networkmovers.modules.ticket.controller.open;

import java.util.UUID; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.ticket.dto.response.TicketResponse;
import com.company.networkmovers.modules.ticket.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/ticket")
public class PublicTicketController {

    private final TicketService service;

    public PublicTicketController(TicketService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

