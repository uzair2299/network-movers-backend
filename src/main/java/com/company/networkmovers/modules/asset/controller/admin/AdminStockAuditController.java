package com.company.networkmovers.modules.asset.controller.admin;

import com.company.networkmovers.modules.asset.dto.request.StockAuditRequest;
import com.company.networkmovers.modules.asset.dto.response.StockAuditResponse;
import com.company.networkmovers.modules.asset.service.StockAuditService;
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
@RequestMapping("/api/v1/admin/assets/stock-audits")
@Tag(name = "Admin Stock Audits", description = "Admin API for managing Asset Stock Audits")
@RequiredArgsConstructor
public class AdminStockAuditController {

    private final StockAuditService service;

    @PostMapping
    @Operation(summary = "Log a new physical stock audit")
    public ResponseEntity<StockAuditResponse> create(@RequestBody StockAuditRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update logged stock audit details")
    public ResponseEntity<StockAuditResponse> update(@PathVariable UUID id, @RequestBody StockAuditRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get stock audit details by ID")
    public ResponseEntity<StockAuditResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Search logged stock audits with pagination")
    public ResponseEntity<Page<StockAuditResponse>> getAll(RequestParamDto requestParams) {
        return ResponseEntity.ok(service.getAll(requestParams));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update stock audit status (completing applies adjustments to inventory stock)")
    public ResponseEntity<StockAuditResponse> updateStatus(
            @PathVariable UUID id,
            @RequestParam String status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete logged stock audit")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
