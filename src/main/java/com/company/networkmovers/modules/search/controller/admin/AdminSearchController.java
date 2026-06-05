package com.company.networkmovers.modules.search.controller.admin;

import java.util.UUID;

import com.company.networkmovers.modules.search.dto.request.SearchRequest;
import com.company.networkmovers.modules.search.dto.response.SearchResponse;
import com.company.networkmovers.modules.search.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/search")
public class AdminSearchController {

    private final SearchService service;

    public AdminSearchController(SearchService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SearchResponse> create(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SearchResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SearchResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SearchResponse> update(@PathVariable UUID id, @RequestBody SearchRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
