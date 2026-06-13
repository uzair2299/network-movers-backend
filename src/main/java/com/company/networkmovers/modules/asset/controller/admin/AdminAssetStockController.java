package com.company.networkmovers.modules.asset.controller.admin;

import com.company.networkmovers.modules.asset.dto.request.AssetStockRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetStockResponse;
import com.company.networkmovers.modules.asset.dto.response.AssetTransactionResponse;
import com.company.networkmovers.modules.asset.service.AssetStockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/assets/stock")
@Tag(name = "Admin Asset Stocks & Transactions", description = "Admin API for managing Asset stock levels and audit logs")
@RequiredArgsConstructor
public class AdminAssetStockController {

    private final AssetStockService service;

    @PostMapping("/adjust")
    @Operation(summary = "Manually adjust stock level for an asset in a location")
    public ResponseEntity<AssetStockResponse> adjustStock(
            @RequestParam UUID assetId,
            @RequestParam UUID locationId,
            @RequestParam BigDecimal quantity,
            @RequestParam String transactionType,
            @RequestParam(required = false) String remarks) {
        return ResponseEntity.ok(service.adjustStock(
                assetId, locationId, quantity, transactionType, "MANUAL_ADJUSTMENT", null, remarks));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get specific stock level record by ID")
    public ResponseEntity<AssetStockResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Get stock list, optionally filtered by location ID")
    public ResponseEntity<Page<AssetStockResponse>> getStock(
            @RequestParam(required = false) UUID locationId,
            Pageable pageable) {
        return ResponseEntity.ok(service.getStock(locationId, pageable));
    }

    @GetMapping("/transactions/{assetId}")
    @Operation(summary = "Get stock transaction logs for an asset")
    public ResponseEntity<Page<AssetTransactionResponse>> getTransactions(
            @PathVariable UUID assetId,
            Pageable pageable) {
        return ResponseEntity.ok(service.getTransactions(assetId, pageable));
    }
}
