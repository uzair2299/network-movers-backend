package com.company.networkmovers.modules.asset.controller.admin;

import com.company.networkmovers.modules.asset.dto.request.MoveAssetUsageRequest;
import com.company.networkmovers.modules.asset.dto.response.MoveAssetUsageResponse;
import com.company.networkmovers.modules.asset.service.MoveAssetUsageService;
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
@RequestMapping("/api/v1/admin/assets/usages")
@Tag(name = "Admin Move Asset Usages", description = "Admin API for managing Asset Usage logs in moving operations")
@RequiredArgsConstructor
public class AdminMoveAssetUsageController {

    private final MoveAssetUsageService service;

    @PostMapping
    @Operation(summary = "Log asset usage allocation for a move")
    public ResponseEntity<MoveAssetUsageResponse> create(@RequestBody MoveAssetUsageRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update logged asset usage details")
    public ResponseEntity<MoveAssetUsageResponse> update(@PathVariable UUID id, @RequestBody MoveAssetUsageRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get asset usage details by ID")
    public ResponseEntity<MoveAssetUsageResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Search asset usage logs with pagination")
    public ResponseEntity<Page<MoveAssetUsageResponse>> getAll(RequestParamDto requestParams) {
        return ResponseEntity.ok(service.getAll(requestParams));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update asset usage status (issuing/returning updates stock)")
    public ResponseEntity<MoveAssetUsageResponse> updateStatus(
            @PathVariable UUID id,
            @RequestParam String status,
            @RequestParam UUID locationId) {
        return ResponseEntity.ok(service.updateStatus(id, status, locationId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete asset usage log")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
