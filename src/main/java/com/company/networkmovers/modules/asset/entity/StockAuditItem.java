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

@Entity
@Table(name = "ast_stock_audit_items")
@SQLRestriction("deleted = false")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StockAuditItem extends BaseUuidSoftDeleteEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_audit_id", nullable = false)
    private StockAudit stockAudit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private AssetLocation location;

    @Column(name = "expected_quantity", nullable = false, precision = 18, scale = 2)
    private BigDecimal expectedQuantity;

    @Column(name = "actual_quantity", nullable = false, precision = 18, scale = 2)
    private BigDecimal actualQuantity;

    @Column(name = "discrepancy", nullable = false, precision = 18, scale = 2)
    private BigDecimal discrepancy;

    @Column(name = "remarks", length = 500)
    private String remarks;
}
