package com.company.networkmovers.modules.asset.entity;

import com.company.networkmovers.shared.entity.BaseUuidSoftDeleteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ast_stock_audits")
@SQLRestriction("deleted = false")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StockAudit extends BaseUuidSoftDeleteEntity {

    @Column(name = "audit_date", nullable = false)
    private LocalDate auditDate;

    @Column(name = "auditor_id", nullable = false)
    private UUID auditorId;

    @Column(name = "remarks", length = 500)
    private String remarks;

    @Column(name = "status", nullable = false)
    private String status; // DRAFT, IN_PROGRESS, COMPLETED, CANCELLED

    @OneToMany(mappedBy = "stockAudit", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<StockAuditItem> items = new ArrayList<>();
}
