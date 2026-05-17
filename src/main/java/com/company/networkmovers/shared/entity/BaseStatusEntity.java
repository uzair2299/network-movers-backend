package com.company.networkmovers.shared.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseStatusEntity extends BaseAuditEntity {

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "status_notes")
    private String statusNotes;
}
