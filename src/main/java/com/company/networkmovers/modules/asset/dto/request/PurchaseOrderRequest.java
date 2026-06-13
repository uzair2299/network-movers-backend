package com.company.networkmovers.modules.asset.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderRequest {
    private String purchaseOrderNumber;
    private UUID supplierId;
    private LocalDate orderDate;
    private LocalDate expectedDeliveryDate;
    private String status; // DRAFT, SUBMITTED, RECEIVED, CANCELLED
    private List<PurchaseOrderItemRequest> items;
}
