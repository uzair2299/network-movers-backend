package com.company.networkmovers.modules.asset.controller.admin;

import com.company.networkmovers.modules.asset.dto.request.AssetCompanyRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetCompanyResponse;
import com.company.networkmovers.modules.asset.service.AssetCompanyService;
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
@RequestMapping("/api/v1/admin/assets/companies")
@Tag(name = "Admin Asset Companies", description = "Admin API for managing Asset Companies")
@RequiredArgsConstructor
public class AdminAssetCompanyController {

    private final AssetCompanyService service;

    @PostMapping
    @Operation(summary = "Create a new Asset Company")
    public ResponseEntity<AssetCompanyResponse> create(@RequestBody AssetCompanyRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Asset Company")
    public ResponseEntity<AssetCompanyResponse> update(@PathVariable UUID id, @RequestBody AssetCompanyRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Asset Company by ID")
    public ResponseEntity<AssetCompanyResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Search Asset Companies with pagination")
    public ResponseEntity<Page<AssetCompanyResponse>> getAll(RequestParamDto requestParams) {
        return ResponseEntity.ok(service.getAll(requestParams));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Asset Company")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
