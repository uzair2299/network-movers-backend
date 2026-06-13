package com.company.networkmovers.modules.asset.controller.admin;

import com.company.networkmovers.modules.asset.dto.request.AssetSupplierRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetSupplierResponse;
import com.company.networkmovers.modules.asset.service.AssetSupplierService;
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
@RequestMapping("/api/v1/admin/assets/suppliers")
@Tag(name = "Admin Asset Suppliers", description = "Admin API for managing Asset Suppliers")
@RequiredArgsConstructor
public class AdminAssetSupplierController {

    private final AssetSupplierService service;

    @PostMapping
    @Operation(summary = "Create a new Asset Supplier")
    public ResponseEntity<AssetSupplierResponse> create(@RequestBody AssetSupplierRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Asset Supplier")
    public ResponseEntity<AssetSupplierResponse> update(@PathVariable UUID id, @RequestBody AssetSupplierRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Asset Supplier by ID")
    public ResponseEntity<AssetSupplierResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Search Asset Suppliers with pagination")
    public ResponseEntity<Page<AssetSupplierResponse>> getAll(RequestParamDto requestParams) {
        return ResponseEntity.ok(service.getAll(requestParams));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Asset Supplier")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
