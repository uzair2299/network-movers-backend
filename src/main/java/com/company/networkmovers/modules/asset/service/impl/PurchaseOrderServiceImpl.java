package com.company.networkmovers.modules.asset.service.impl;

import com.company.networkmovers.modules.asset.dto.request.PurchaseOrderItemRequest;
import com.company.networkmovers.modules.asset.dto.request.PurchaseOrderRequest;
import com.company.networkmovers.modules.asset.dto.response.PurchaseOrderResponse;
import com.company.networkmovers.modules.asset.entity.*;
import com.company.networkmovers.modules.asset.mapper.PurchaseOrderMapper;
import com.company.networkmovers.modules.asset.repository.AssetRepository;
import com.company.networkmovers.modules.asset.repository.AssetSupplierRepository;
import com.company.networkmovers.modules.asset.repository.PurchaseOrderRepository;
import com.company.networkmovers.modules.asset.service.AssetStockService;
import com.company.networkmovers.modules.asset.service.PurchaseOrderService;
import com.company.networkmovers.shared.dto.RequestParamDto;
import com.company.networkmovers.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository repository;
    private final PurchaseOrderMapper mapper;
    private final AssetSupplierRepository supplierRepository;
    private final AssetRepository assetRepository;
    private final AssetStockService stockService;

    @Override
    @Transactional
    public PurchaseOrderResponse create(PurchaseOrderRequest request) {
        if (repository.existsByPurchaseOrderNumberAndDeletedFalse(request.getPurchaseOrderNumber())) {
            throw new IllegalArgumentException("Purchase order number " + request.getPurchaseOrderNumber() + " already exists.");
        }

        AssetSupplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + request.getSupplierId()));

        PurchaseOrder entity = mapper.toEntity(request);
        entity.setSupplier(supplier);
        entity.setStatus(request.getStatus().toUpperCase());

        BigDecimal total = BigDecimal.ZERO;
        List<PurchaseOrderItem> items = new ArrayList<>();

        if (request.getItems() != null) {
            for (PurchaseOrderItemRequest itemReq : request.getItems()) {
                Asset asset = assetRepository.findById(itemReq.getAssetId())
                        .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + itemReq.getAssetId()));

                BigDecimal itemTotal = itemReq.getQuantity().multiply(itemReq.getUnitPrice());
                total = total.add(itemTotal);

                PurchaseOrderItem item = PurchaseOrderItem.builder()
                        .purchaseOrder(entity)
                        .asset(asset)
                        .quantity(itemReq.getQuantity())
                        .unitPrice(itemReq.getUnitPrice())
                        .totalPrice(itemTotal)
                        .build();
                items.add(item);
            }
        }

        entity.setTotalAmount(total);
        entity.setItems(items);

        PurchaseOrder saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public PurchaseOrderResponse update(UUID id, PurchaseOrderRequest request) {
        PurchaseOrder entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseOrder not found with id " + id));

        if (repository.existsByPurchaseOrderNumberAndIdNotAndDeletedFalse(request.getPurchaseOrderNumber(), id)) {
            throw new IllegalArgumentException("Purchase order number " + request.getPurchaseOrderNumber() + " already exists.");
        }

        AssetSupplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id " + request.getSupplierId()));

        entity.setPurchaseOrderNumber(request.getPurchaseOrderNumber());
        entity.setSupplier(supplier);
        entity.setOrderDate(request.getOrderDate());
        entity.setExpectedDeliveryDate(request.getExpectedDeliveryDate());

        // Update items list
        entity.getItems().clear();

        BigDecimal total = BigDecimal.ZERO;
        if (request.getItems() != null) {
            for (PurchaseOrderItemRequest itemReq : request.getItems()) {
                Asset asset = assetRepository.findById(itemReq.getAssetId())
                        .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + itemReq.getAssetId()));

                BigDecimal itemTotal = itemReq.getQuantity().multiply(itemReq.getUnitPrice());
                total = total.add(itemTotal);

                PurchaseOrderItem item = PurchaseOrderItem.builder()
                        .purchaseOrder(entity)
                        .asset(asset)
                        .quantity(itemReq.getQuantity())
                        .unitPrice(itemReq.getUnitPrice())
                        .totalPrice(itemTotal)
                        .build();
                entity.getItems().add(item);
            }
        }

        entity.setTotalAmount(total);
        // Only allow status update if not already received
        if (!"RECEIVED".equalsIgnoreCase(entity.getStatus())) {
            entity.setStatus(request.getStatus().toUpperCase());
        }

        PurchaseOrder saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseOrderResponse getById(UUID id) {
        return repository.findByIdWithDetails(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseOrder not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PurchaseOrderResponse> getAll(RequestParamDto requestParams) {
        String[] sortParams = requestParams.getSort().split(",");
        String sortField = sortParams[0];
        Sort.Direction direction = Sort.Direction.ASC;
        if (sortParams.length > 1 && "desc".equalsIgnoreCase(sortParams[1])) {
            direction = Sort.Direction.DESC;
        }
        Pageable pageable = PageRequest.of(
                requestParams.getPage(),
                requestParams.getSize(),
                Sort.by(direction, sortField)
        );
        return repository.findAllActive(pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public PurchaseOrderResponse updateStatus(UUID id, String status, UUID receivingLocationId) {
        PurchaseOrder entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseOrder not found with id " + id));

        String oldStatus = entity.getStatus();
        String newStatus = status.toUpperCase();

        if ("RECEIVED".equalsIgnoreCase(oldStatus)) {
            throw new IllegalStateException("Cannot change status of an already RECEIVED purchase order.");
        }

        entity.setStatus(newStatus);

        if ("RECEIVED".equalsIgnoreCase(newStatus)) {
            if (receivingLocationId == null) {
                throw new IllegalArgumentException("Receiving location ID is required when receiving a purchase order.");
            }
            // Increment stock for all items
            for (PurchaseOrderItem item : entity.getItems()) {
                stockService.adjustStock(
                        item.getAsset().getId(),
                        receivingLocationId,
                        item.getQuantity(),
                        "IN",
                        "PURCHASE_ORDER",
                        entity.getId(),
                        "Received items from PO #" + entity.getPurchaseOrderNumber()
                );
            }
        }

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        PurchaseOrder entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseOrder not found with id " + id));
        entity.delete(null);
        repository.save(entity);
    }
}
