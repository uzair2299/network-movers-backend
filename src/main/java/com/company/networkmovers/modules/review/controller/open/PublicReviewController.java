package com.company.networkmovers.modules.review.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.review.dto.response.ReviewResponse;
import com.company.networkmovers.modules.review.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/review")
public class PublicReviewController {

    private final ReviewService service;

    public PublicReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

