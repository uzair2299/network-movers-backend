package com.company.networkmovers.modules.document.repository;

import java.util.UUID;

import com.company.networkmovers.modules.document.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, UUID> {
}
