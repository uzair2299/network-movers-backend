package com.company.networkmovers.modules.asset.entity;

import com.company.networkmovers.shared.entity.BaseUuidSoftDeleteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "ast_asset_transactions")
@SQLRestriction("deleted = false")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AssetTransaction extends BaseUuidSoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType; // IN, OUT, TRANSFER, ADJUST, MAINTENANCE, DAMAGE

    @Column(name = "quantity", nullable = false, precision = 18, scale = 2)
    private BigDecimal quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_location_id")
    private AssetLocation sourceLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_location_id")
    private AssetLocation destinationLocation;

    @Column(name = "reference_type")
    private String referenceType; // PURCHASE_ORDER, BOOKING, STOCK_AUDIT, DAMAGE_REPORT, MAINTENANCE

    @Column(name = "reference_id")
    private UUID referenceId;

    @Column(name = "remarks", length = 500)
    private String remarks;
}
