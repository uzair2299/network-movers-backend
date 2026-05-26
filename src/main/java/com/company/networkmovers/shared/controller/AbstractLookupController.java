package com.company.networkmovers.shared.controller;

import com.company.networkmovers.shared.service.GenericLookupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public abstract class AbstractLookupController<REQ, RES> {

    protected final GenericLookupService<REQ, RES> service;

    protected AbstractLookupController(GenericLookupService<REQ, RES> service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RES> create(@RequestBody REQ request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RES> update(@PathVariable UUID id, @RequestBody REQ request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RES> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/active")
    public ResponseEntity<List<RES>> getAllActive() {
        return ResponseEntity.ok(service.getAllActive());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
