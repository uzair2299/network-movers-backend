package com.company.networkmovers.modules.asset.service;

import com.company.networkmovers.modules.asset.dto.request.AssetStockRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetStockResponse;
import com.company.networkmovers.modules.asset.dto.response.AssetTransactionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.UUID;

public interface AssetStockService {
    AssetStockResponse adjustStock(UUID assetId, UUID locationId, BigDecimal quantity,
                                   String transactionType, String referenceType, UUID referenceId, String remarks);
    AssetStockResponse getById(UUID id);
    Page<AssetStockResponse> getStock(UUID locationId, Pageable pageable);
    Page<AssetTransactionResponse> getTransactions(UUID assetId, Pageable pageable);
}
