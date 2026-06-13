package com.company.networkmovers.modules.asset.controller.admin;

import com.company.networkmovers.modules.asset.dto.request.PurchaseOrderRequest;
import com.company.networkmovers.modules.asset.dto.response.PurchaseOrderResponse;
import com.company.networkmovers.modules.asset.service.PurchaseOrderService;
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
@RequestMapping("/api/v1/admin/assets/purchase-orders")
@Tag(name = "Admin Purchase Orders", description = "Admin API for managing Asset Purchase Orders")
@RequiredArgsConstructor
public class AdminPurchaseOrderController {

    private final PurchaseOrderService service;

    @PostMapping
    @Operation(summary = "Create a new Purchase Order")
    public ResponseEntity<PurchaseOrderResponse> create(@RequestBody PurchaseOrderRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Purchase Order")
    public ResponseEntity<PurchaseOrderResponse> update(@PathVariable UUID id, @RequestBody PurchaseOrderRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Purchase Order details by ID")
    public ResponseEntity<PurchaseOrderResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Search Purchase Orders with pagination")
    public ResponseEntity<Page<PurchaseOrderResponse>> getAll(RequestParamDto requestParams) {
        return ResponseEntity.ok(service.getAll(requestParams));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update Purchase Order status (receipt updates inventory stock)")
    public ResponseEntity<PurchaseOrderResponse> updateStatus(
            @PathVariable UUID id,
            @RequestParam String status,
            @RequestParam(required = false) UUID receivingLocationId) {
        return ResponseEntity.ok(service.updateStatus(id, status, receivingLocationId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Purchase Order record")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
