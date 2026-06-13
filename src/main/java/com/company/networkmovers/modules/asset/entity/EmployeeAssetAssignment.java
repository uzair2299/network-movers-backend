package com.company.networkmovers.modules.asset.entity;

import com.company.networkmovers.shared.entity.BaseUuidSoftDeleteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "employee_asset_assignments")
@SQLRestriction("deleted = false")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAssetAssignment extends BaseUuidSoftDeleteEntity {

    @Column(name = "employee_id", nullable = false)
    private UUID employeeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @Column(name = "assigned_date", nullable = false)
    private LocalDate assignedDate;

    @Column(name = "returned_date")
    private LocalDate returnedDate;

    @Column(name = "condition_on_issue")
    private String conditionOnIssue;

    @Column(name = "condition_on_return")
    private String conditionOnReturn;

    @Column(name = "remarks", length = 500)
    private String remarks;

    @Column(name = "status", nullable = false)
    private String status; // ASSIGNED, RETURNED
}
