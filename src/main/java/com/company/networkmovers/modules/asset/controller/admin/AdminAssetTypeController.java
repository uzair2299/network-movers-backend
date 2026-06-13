package com.company.networkmovers.modules.asset.controller.admin;

import com.company.networkmovers.modules.asset.dto.request.AssetTypeRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetTypeResponse;
import com.company.networkmovers.modules.asset.service.AssetTypeService;
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
@RequestMapping("/api/v1/admin/assets/types")
@Tag(name = "Admin Asset Types", description = "Admin API for managing Asset Types")
@RequiredArgsConstructor
public class AdminAssetTypeController {

    private final AssetTypeService service;

    @PostMapping
    @Operation(summary = "Create a new Asset Type")
    public ResponseEntity<AssetTypeResponse> create(@RequestBody AssetTypeRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Asset Type")
    public ResponseEntity<AssetTypeResponse> update(@PathVariable UUID id, @RequestBody AssetTypeRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Asset Type by ID")
    public ResponseEntity<AssetTypeResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Search Asset Types with pagination")
    public ResponseEntity<Page<AssetTypeResponse>> getAll(RequestParamDto requestParams) {
        return ResponseEntity.ok(service.getAll(requestParams));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Asset Type")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
