package com.company.networkmovers.modules.asset.controller.admin;

import com.company.networkmovers.modules.asset.dto.request.AssetCategoryRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetCategoryResponse;
import com.company.networkmovers.modules.asset.service.AssetCategoryService;
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
@RequestMapping("/api/v1/admin/assets/categories")
@Tag(name = "Admin Asset Categories", description = "Admin API for managing Asset Categories")
@RequiredArgsConstructor
public class AdminAssetCategoryController {

    private final AssetCategoryService service;

    @PostMapping
    @Operation(summary = "Create a new Asset Category")
    public ResponseEntity<AssetCategoryResponse> create(@RequestBody AssetCategoryRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Asset Category")
    public ResponseEntity<AssetCategoryResponse> update(@PathVariable UUID id, @RequestBody AssetCategoryRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Asset Category by ID")
    public ResponseEntity<AssetCategoryResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Search Asset Categories with pagination")
    public ResponseEntity<Page<AssetCategoryResponse>> getAll(RequestParamDto requestParams) {
        return ResponseEntity.ok(service.getAll(requestParams));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Asset Category")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
