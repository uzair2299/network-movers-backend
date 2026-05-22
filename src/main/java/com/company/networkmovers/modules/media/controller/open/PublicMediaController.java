package com.company.networkmovers.modules.media.controller.open; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.media.dto.response.MediaResponse;
import com.company.networkmovers.modules.media.service.MediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/media")
public class PublicMediaController {

    private final MediaService service;

    public PublicMediaController(MediaService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<MediaResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

