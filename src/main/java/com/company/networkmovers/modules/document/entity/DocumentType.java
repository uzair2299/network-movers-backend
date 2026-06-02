package com.company.networkmovers.modules.document.entity;

import com.company.networkmovers.shared.entity.BaseLookupEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "document_types")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentType extends BaseLookupEntity {

    @Column(name = "mandatory")
    @Builder.Default
    private boolean mandatory = false;

    @Column(name = "expiry_required")
    @Builder.Default
    private boolean expiryRequired = true;

    @Column(name = "description")
    private String description;
}
