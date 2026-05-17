package com.company.networkmovers.modules.rating.controller.publics; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.rating.dto.response.RatingResponse;
import com.company.networkmovers.modules.rating.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/rating")
public class PublicRatingController {

    private final RatingService service;

    public PublicRatingController(RatingService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RatingResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
