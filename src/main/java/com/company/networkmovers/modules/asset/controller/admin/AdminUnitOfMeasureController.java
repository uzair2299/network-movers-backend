package com.company.networkmovers.modules.asset.controller.admin;

import com.company.networkmovers.modules.asset.dto.request.UnitOfMeasureRequest;
import com.company.networkmovers.modules.asset.dto.response.UnitOfMeasureResponse;
import com.company.networkmovers.modules.asset.service.UnitOfMeasureService;
import com.company.networkmovers.shared.dto.RequestParamDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/assets/units-of-measure")
@Tag(name = "Admin Asset Units of Measure", description = "Admin API for managing Asset Units of Measure")
@RequiredArgsConstructor
public class AdminUnitOfMeasureController {

    private final UnitOfMeasureService service;

    @PostMapping
    @Operation(summary = "Create a new Unit of Measure")
    public ResponseEntity<UnitOfMeasureResponse> create(@RequestBody UnitOfMeasureRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Unit of Measure")
    public ResponseEntity<UnitOfMeasureResponse> update(@PathVariable UUID id, @RequestBody UnitOfMeasureRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Unit of Measure by ID")
    public ResponseEntity<UnitOfMeasureResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Search Units of Measure with pagination")
    public ResponseEntity<Page<UnitOfMeasureResponse>> getAll(RequestParamDto requestParams) {
        return ResponseEntity.ok(service.getAll(requestParams));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Unit of Measure")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
