package com.company.networkmovers.modules.search.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.search.dto.response.SearchResponse;
import com.company.networkmovers.modules.search.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/search")
public class PublicSearchController {

    private final SearchService service;

    public PublicSearchController(SearchService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SearchResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SearchResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
