package com.company.networkmovers.modules.filemanagement.repository;

import com.company.networkmovers.modules.filemanagement.entity.FilemanagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilemanagementRepository extends JpaRepository<FilemanagementEntity, Long> {
}
