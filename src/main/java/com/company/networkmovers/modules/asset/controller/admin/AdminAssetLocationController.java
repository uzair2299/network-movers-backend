package com.company.networkmovers.modules.asset.controller.admin;

import com.company.networkmovers.modules.asset.dto.request.AssetLocationRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetLocationResponse;
import com.company.networkmovers.modules.asset.service.AssetLocationService;
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
@RequestMapping("/api/v1/admin/assets/locations")
@Tag(name = "Admin Asset Locations", description = "Admin API for managing Asset Locations")
@RequiredArgsConstructor
public class AdminAssetLocationController {

    private final AssetLocationService service;

    @PostMapping
    @Operation(summary = "Create a new Asset Location")
    public ResponseEntity<AssetLocationResponse> create(@RequestBody AssetLocationRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Asset Location")
    public ResponseEntity<AssetLocationResponse> update(@PathVariable UUID id, @RequestBody AssetLocationRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Asset Location by ID")
    public ResponseEntity<AssetLocationResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Search Asset Locations with pagination")
    public ResponseEntity<Page<AssetLocationResponse>> getAll(RequestParamDto requestParams) {
        return ResponseEntity.ok(service.getAll(requestParams));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Asset Location")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
