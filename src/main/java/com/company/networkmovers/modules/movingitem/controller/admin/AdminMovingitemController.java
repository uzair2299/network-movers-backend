package com.company.networkmovers.modules.movingitem.controller.admin;

import com.company.networkmovers.modules.movingitem.dto.request.MovingitemRequest;
import com.company.networkmovers.modules.movingitem.dto.response.MovingitemResponse;
import com.company.networkmovers.modules.movingitem.service.MovingitemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/movingitem")
public class AdminMovingitemController {

    private final MovingitemService service;

    public AdminMovingitemController(MovingitemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MovingitemResponse> create(@RequestBody MovingitemRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovingitemResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<MovingitemResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovingitemResponse> update(@PathVariable Long id, @RequestBody MovingitemRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
