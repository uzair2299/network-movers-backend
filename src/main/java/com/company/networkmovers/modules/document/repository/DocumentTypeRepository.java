package com.company.networkmovers.modules.document.repository;

import com.company.networkmovers.modules.document.entity.DocumentType;
import com.company.networkmovers.shared.repository.BaseLookupRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DocumentTypeRepository extends BaseLookupRepository<DocumentType> {
}
