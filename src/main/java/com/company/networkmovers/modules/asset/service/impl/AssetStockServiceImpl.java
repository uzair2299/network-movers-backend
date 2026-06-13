package com.company.networkmovers.modules.asset.service.impl;

import com.company.networkmovers.modules.asset.dto.response.AssetStockResponse;
import com.company.networkmovers.modules.asset.dto.response.AssetTransactionResponse;
import com.company.networkmovers.modules.asset.entity.*;
import com.company.networkmovers.modules.asset.mapper.AssetStockMapper;
import com.company.networkmovers.modules.asset.mapper.AssetTransactionMapper;
import com.company.networkmovers.modules.asset.repository.*;
import com.company.networkmovers.modules.asset.service.AssetStockService;
import com.company.networkmovers.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssetStockServiceImpl implements AssetStockService {

    private final AssetStockRepository repository;
    private final AssetStockMapper mapper;
    private final AssetRepository assetRepository;
    private final AssetLocationRepository locationRepository;
    private final AssetTransactionRepository transactionRepository;
    private final AssetTransactionMapper transactionMapper;

    @Override
    @Transactional
    public AssetStockResponse adjustStock(UUID assetId, UUID locationId, BigDecimal quantity,
                                           String transactionType, String referenceType, UUID referenceId, String remarks) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + assetId));
        AssetLocation location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("AssetLocation not found with id " + locationId));

        AssetStock stock = repository.findByAssetIdAndLocationId(assetId, locationId)
                .orElseGet(() -> AssetStock.builder()
                        .asset(asset)
                        .location(location)
                        .quantity(BigDecimal.ZERO)
                        .minimumQuantity(BigDecimal.ZERO)
                        .build());

        BigDecimal oldQty = stock.getQuantity();
        BigDecimal newQty;

        if ("IN".equalsIgnoreCase(transactionType)) {
            newQty = oldQty.add(quantity);
        } else if ("OUT".equalsIgnoreCase(transactionType)) {
            newQty = oldQty.subtract(quantity);
        } else if ("ADJUST".equalsIgnoreCase(transactionType)) {
            newQty = quantity;
        } else {
            newQty = oldQty.add(quantity);
        }

        stock.setQuantity(newQty);
        AssetStock savedStock = repository.save(stock);

        // Record Transaction Log
        AssetLocation srcLoc = "OUT".equalsIgnoreCase(transactionType) ? location : null;
        AssetLocation dstLoc = "IN".equalsIgnoreCase(transactionType) ? location : null;
        if ("ADJUST".equalsIgnoreCase(transactionType)) {
            dstLoc = location;
        }

        AssetTransaction tx = AssetTransaction.builder()
                .asset(asset)
                .transactionType(transactionType.toUpperCase())
                .quantity(quantity)
                .sourceLocation(srcLoc)
                .destinationLocation(dstLoc)
                .referenceType(referenceType)
                .referenceId(referenceId)
                .remarks(remarks)
                .build();
        transactionRepository.save(tx);

        return mapper.toResponse(savedStock);
    }

    @Override
    @Transactional(readOnly = true)
    public AssetStockResponse getById(UUID id) {
        return repository.findByIdWithDetails(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("AssetStock not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AssetStockResponse> getStock(UUID locationId, Pageable pageable) {
        return repository.findAllActive(locationId, pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AssetTransactionResponse> getTransactions(UUID assetId, Pageable pageable) {
        return transactionRepository.findByAssetId(assetId, pageable).map(transactionMapper::toResponse);
    }
}
